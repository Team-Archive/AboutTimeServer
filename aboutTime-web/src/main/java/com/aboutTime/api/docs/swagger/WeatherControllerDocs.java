package com.aboutTime.api.docs.swagger;

import com.aboutTime.entity.post.weather.Weather;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WeatherControllerDocs {

    @Operation(summary = "특정 도시의 날씨 데이터 조회")
    ResponseEntity<Weather> getWeatherByCity(@Parameter(name="city") String city);

    @Operation(summary = "특정 도시의 날씨 데이터 생성", description = "이미 존재한다면 업데이트")
    ResponseEntity<Weather> saveWeatherData(@Parameter(name="city") String city);

    @Operation(summary = "모든 도시의 날씨 데이터 조회")
    ResponseEntity<List<Weather>> getAllWeatherData();

}
