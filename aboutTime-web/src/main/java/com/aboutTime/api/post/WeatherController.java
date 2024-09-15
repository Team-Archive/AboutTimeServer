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
        var weather = weatherService.findWeatherById(id);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Weather> getWeatherByCountryCode(@RequestParam("city") String city) {
        try {
            Weather weather = weatherService.getWeatherIfHourMatches(city);
            return ResponseEntity.ok(weather);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherById(@PathVariable("id") Long id) {
        try {
            weatherService.deleteWeatherById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/trigger-update")
    public ResponseEntity<String> triggerUpdate() {
        weatherService.updateWeatherData();
        return ResponseEntity.ok("Weather data updated");
    }

}