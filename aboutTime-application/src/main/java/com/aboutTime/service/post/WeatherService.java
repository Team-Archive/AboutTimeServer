package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherIcon;
import com.aboutTime.entity.post.weather.WeatherIconRepository;
import com.aboutTime.entity.post.weather.WeatherRepository;
import com.aboutTime.infra.weather.WeatherAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherAPIService weatherAPIService;
    private final WeatherIconRepository weatherIconRepository;

    @Scheduled(cron = "0 0 * * * ?")
    @Transactional
    public void updateWeatherData() {
        System.out.println("updateWeatherData 실행");
        List<Weather> weatherList = weatherRepository.findAll();

        for (Weather weather : weatherList) {
            String city = weather.getCity();
            Weather updatedWeather = weatherAPIService.getWeatherData(city);

            weather.setConditionCode(updatedWeather.getConditionCode());
            weather.setTemperature(updatedWeather.getTemperature());
            weather.setHour(updatedWeather.getHour());

            weatherRepository.save(weather);
        }
        System.out.println("업데이트 완료");
    }

    @Transactional
    public Weather saveWeatherData(String city) {
        Weather updatedWeather = weatherAPIService.getWeatherData(city);

        try {
            // 기존 데이터 조회
            Weather existingWeather = getWeatherByCity(city);

            // 데이터가 존재할 경우 업데이트
            existingWeather.setConditionCode(updatedWeather.getConditionCode());
            existingWeather.setTemperature(updatedWeather.getTemperature());
            existingWeather.setHour(updatedWeather.getHour());
            log.info("Updated weather data for city: {}", city);
            return weatherRepository.save(existingWeather); // 업데이트된 데이터 저장
        } catch (IllegalArgumentException e) {
            // 데이터가 존재하지 않는 경우 새 데이터 생성
            log.info("Creating new weather data for city: {}", city);
            return weatherRepository.save(updatedWeather); // 새 데이터 저장
        }
    }

    public Weather getWeatherByCity(String city) {
        return weatherRepository.findByCity(city)
                .orElseThrow(() -> new ResourceNotFoundException(city + "에 해당하는 날씨 데이터가 없습니다."));
    }

    public Weather getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "에 해당하는 날씨 데이터가 없습니다."));
    }

    public String getWeatherIconUrlByCode(String code) {
        return weatherIconRepository.findByCode(code)
                .map(WeatherIcon::getUrl)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 날씨 아이콘이 없습니다."));
    }

}
