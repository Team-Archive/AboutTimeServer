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

    public Weather getWeatherByCity(String city) {
        return weatherRepository.findByCity(city)
                .orElseThrow(() -> new ResourceNotFoundException(city + "에 해당하는 날씨 데이터가 없습니다."));
    }

    public List<Weather> getAllWeatherData() {
        return weatherRepository.findAll();
    }

    public String getWeatherIconUrlByCode(String code) {
        return weatherIconRepository.findByCode(code)
                .map(WeatherIcon::getUrl)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 날씨 아이콘이 없습니다."));
    }

}