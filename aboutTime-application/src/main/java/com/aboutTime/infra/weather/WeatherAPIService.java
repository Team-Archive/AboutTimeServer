package com.aboutTime.infra.weather;

import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherAPIService {

    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public Mono<Weather> getWeatherData(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("lang", "kr")
                .queryParam("units", "metric")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> processWeatherData(city, jsonNode));
    }

    private Weather processWeatherData(String city, JsonNode jsonNode) {
        int conditionCode = jsonNode.path("weather").get(0).path("id").asInt();
        double temperature = jsonNode.path("main").path("temp").asDouble();
        long dt = jsonNode.path("dt").asLong();
        int timezoneOffset = jsonNode.path("timezone").asInt();

        ZonedDateTime currentTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(dt), ZoneOffset.UTC)
                .withZoneSameInstant(ZoneOffset.ofTotalSeconds(timezoneOffset));
        int hour = currentTime.getHour();

        String iconName = WeatherData.fromConditionCode(conditionCode, hour);

        return Weather.builder()
                .city(city)
                .conditionCode(iconName)
                .temperature(temperature)
                .hour(hour)
                .build();
    }
}
