package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherIcon;
import com.aboutTime.entity.post.weather.WeatherIconRepository;
import com.aboutTime.entity.post.weather.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherIconRepository weatherIconRepository;
//
//    //3시간마다 날씨 정보 업데이트
//    @Scheduled(cron = "0 0 */3 * * ?")
//    @Async
//    @Transactional
//    public void updateWeatherData() {
//        log.info("Weather data update start");
//        List<Weather> weatherList = weatherRepository.findAll();
//
//        for (Weather weather : weatherList) {
//            try {
//                updateWeather(weather);
//            } catch (Exception e) {
//                log.error("Failed to update weather for city: {} - Error: {}", weather.getCity(), e.getMessage());
//            }
//        }
//        log.info("Weather data updated Completed: {}", weatherList.size());
//    }
//
//    @Transactional
//    public Weather saveWeatherData(String city) {
//        Weather updatedWeather = weatherAPIService.getWeatherDataByCity(city);
//        return updateOrSaveWeather(city, updatedWeather);
//    }
//
    public Weather getWeatherByCity(String city) {
        return weatherRepository.findByCity(city)
                .orElseThrow(() -> new ResourceNotFoundException(city + "에 해당하는 날씨 데이터가 없습니다."));
    }

    public List<Weather> getAllWeatherData() {
        return weatherRepository.findAll();
    }
//
//    private void updateWeather(Weather weather) {
//        String city = weather.getCity();
//        Weather updatedWeather = weatherAPIService.getWeatherDataByCity(city);
//        applyUpdatedWeatherData(weather, updatedWeather);
//    }
//
//    private Weather updateOrSaveWeather(String city, Weather updatedWeather) {
//        return weatherRepository.findByCity(city)
//                .map(existingWeather -> {
//                    applyUpdatedWeatherData(existingWeather, updatedWeather);
//                    log.info("Updated weather data for city: {}", city);
//                    return weatherRepository.save(existingWeather);
//                })
//                .orElseGet(() -> {
//                    log.info("Creating new weather data for city: {}", city);
//                    return weatherRepository.save(updatedWeather);
//                });
//    }

    public String getWeatherIconUrlByCode(String code) {
        return weatherIconRepository.findByCode(code)
                .map(WeatherIcon::getUrl)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 날씨 아이콘이 없습니다."));
    }

}