package com.aboutTime.infra;

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
    private final ObjectMapper objectMapper;

    private static final String QUERY_PARAM_CITY = "q";
    private static final String QUERY_PARAM_APPID = "appid";
    private static final String QUERY_PARAM_UNITS = "units";
    private static final String UNITS_METRIC = "metric";

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public Weather getWeatherDataByCity(String city) {
        String url = buildWeatherApiUrl(city);
        String response = requestWeatherData(url);
        JsonNode jsonNode = parseWeatherResponse(response);
        return createWeatherData(city, jsonNode);
    }

    private String buildWeatherApiUrl(String city) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam(QUERY_PARAM_CITY, city)
                .queryParam(QUERY_PARAM_APPID, apiKey)
                .queryParam(QUERY_PARAM_UNITS, UNITS_METRIC)
                .toUriString();
    }

    private String requestWeatherData(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new RuntimeException("날씨 데이터 요청 실패: " + e.getMessage(), e);
        }
    }

    private JsonNode parseWeatherResponse(String response) {
        try {
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("날씨 데이터 파싱 실패: " + e.getMessage(), e);
        }
    }

    private Weather createWeatherData(String city, JsonNode jsonNode) {
        int conditionCode = extractConditionCode(jsonNode);
        double temperature = extractTemperature(jsonNode);
        int hour = getCurrentHour(jsonNode);
        String iconName = WeatherData.fromConditionCode(conditionCode, hour);

        return Weather.builder()
                .city(city)
                .conditionCode(iconName)
                .temperature(temperature)
                .hour(hour)
                .build();
    }

    private int extractConditionCode(JsonNode jsonNode) {
        return jsonNode.path("weather").get(0).path("id").asInt();
    }

    private double extractTemperature(JsonNode jsonNode) {
        return jsonNode.path("main").path("temp").asDouble();
    }

    private int getCurrentHour(JsonNode jsonNode) {
        long timestamp  = jsonNode.path("dt").asLong();
        int timezoneOffset = jsonNode.path("timezone").asInt();

        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC)
                .withZoneSameInstant(ZoneOffset.ofTotalSeconds(timezoneOffset)).getHour();
    }

}
