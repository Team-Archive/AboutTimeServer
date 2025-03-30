package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.WeatherControllerDocs;
import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.service.post.WeatherService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/weather")
@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherControllerDocs {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<Weather> getWeatherByCity(@RequestParam String city) {
        var weather = weatherService.getWeatherByCity(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> saveWeatherData(@RequestParam String city) {
        Weather weather = weatherService.saveWeatherData(city);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Weather>> getAllWeatherData() {
        List<Weather> weatherList = weatherService.getAllWeatherData();
        return ResponseEntity.ok(weatherList);
    }

}