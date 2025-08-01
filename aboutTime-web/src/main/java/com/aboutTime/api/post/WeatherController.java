package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.WeatherControllerDocs;
import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.infra.WeatherApiService;
import com.aboutTime.service.post.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/weather")
@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherControllerDocs {

    private final WeatherService weatherService;
    private final WeatherApiService weatherApiService;

    @GetMapping
    public ResponseEntity<Weather> getWeatherByCity(@RequestParam String city) {
        var weather = weatherService.getWeatherByCity(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Weather> saveWeatherData(@RequestParam String city) {
//        Weather weather = weatherService.saveWeatherData(city);
//        return ResponseEntity.ok(weather);
//    }

    @GetMapping("/all")
    public ResponseEntity<List<Weather>> getAllWeatherData() {
        List<Weather> weatherList = weatherService.getAllWeatherData();
        return ResponseEntity.ok(weatherList);
    }

    @PostMapping("/update")
    public ResponseEntity<String> fetchAllWeatherData() {
        try {
            weatherApiService.fetchWeatherData();
            return ResponseEntity.ok("날씨 데이터 업데이트 완료");
        } catch (Exception e) {
            log.error("날씨 데이터 업데이트 실패", e);
            return ResponseEntity.status(500).body("날씨 데이터 업데이트 중 오류 발생");
        }
    }

}