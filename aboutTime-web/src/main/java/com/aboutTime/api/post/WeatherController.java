package com.aboutTime.api.post;

import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.service.post.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/weather")
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable("id") Long id) {
        var weather = weatherService.getWeatherById(id);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<Weather> getWeatherByCity(@RequestParam String city) {
        var weather = weatherService.getWeatherByCity(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> saveWeatherData(@RequestParam String city) {
        Weather weather = weatherService.saveWeatherData(city);
        return ResponseEntity.ok(weather);
    }

}