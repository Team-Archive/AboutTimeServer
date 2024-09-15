package com.aboutTime.service.post;

import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherRepository;
import com.aboutTime.infra.weather.WeatherAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherAPIService weatherAPIService;

    private final int updateInterval = 3600000;     //1시간

    @Transactional
    @Scheduled(fixedRate = updateInterval)
    public void updateWeatherData() {
        log.info("Starting weather data update...");

        LocalDateTime cutoffTime = LocalDateTime.now().minusMinutes(updateInterval / 60000);
        List<Weather> weatherList = weatherRepository.findByUpdatedAtBefore(cutoffTime);
        log.info("Found {} weather records to update", weatherList.size());

        weatherList.stream()
                .map(this::updateWeatherForLocation)
                .forEach(Mono::subscribe);

        Mono.when(
                        weatherList.stream().map(this::updateWeatherForLocation).toList()
                ).doOnSuccess(unused -> log.info("Successfully updated all weather records"))
                .doOnError(error -> log.error("Error during weather data update", error))
                .subscribe();
    }

    private Mono<Weather> updateWeatherForLocation(Weather weather) {
        return weatherAPIService.getWeatherData(weather.getCity())
                .flatMap(updatedWeather -> {
                    log.info("Updating weather data for {}", weather.getCity());
                    weather.setConditionCode(updatedWeather.getConditionCode());
                    weather.setTemperature(updatedWeather.getTemperature());
                    weather.setHour(updatedWeather.getHour());
                    return saveWeather(weather);
                })
                .doOnError(error -> log.error("Failed to update weather data for {}: {}", weather.getCity(), error.getMessage()));
    }

    @Transactional
    public Mono<Weather> saveWeather(Weather weather) {
        return Mono.fromCallable(() -> weatherRepository.save(weather))
                .doOnSuccess(savedWeather -> log.info("Weather data saved for {}", savedWeather.getCity()))
                .doOnError(error -> log.error("Error saving weather data: {}", error.getMessage()));
    }

    public Weather getWeatherIfHourMatches(String city) {
        int currentHour = LocalDateTime.now().getHour();
        log.info("Fetching weather data for city: {}", city);

        Weather weatherData = weatherRepository.findByCity(city)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 날씨 데이터가 없습니다."));

        if (weatherData.getHour() != currentHour) {
            throw new IllegalStateException("현재 시간과 일치하는 날씨 데이터가 없습니다.");
        }

        log.info("Weather data found and hour matches: {}", weatherData);
        return weatherData;
    }

    public Weather findWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 날씨 데이터가 없습니다."));
    }

    @Transactional
    public void deleteWeatherById(Long id) {
        weatherRepository.deleteById(id);
    }

}
