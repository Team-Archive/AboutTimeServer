package com.aboutTime.infra.weather;

import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherAPIService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    //city에 따른 날씨 정보 업데이트
    public Weather getWeatherData(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("lang", "kr")
                .queryParam("units", "metric")
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            return processWeatherData(city, jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("날씨 데이터 업데이트 실패");
        }
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
