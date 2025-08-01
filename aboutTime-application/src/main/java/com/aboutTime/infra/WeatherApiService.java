package com.aboutTime.infra;

import com.aboutTime.entity.post.weather.Weather;
import com.aboutTime.entity.post.weather.WeatherRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherApiService {

    private static final int BATCH_SIZE = 20;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WeatherRepository weatherRepository;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Transactional
    public void fetchWeatherData() {
        List<Weather> allWeatherEntities = weatherRepository.findAll();

        for (int i = 0; i < allWeatherEntities.size(); i += BATCH_SIZE) {
            List<Weather> subList = allWeatherEntities.subList(i, Math.min(i + 20, allWeatherEntities.size()));
            String ids = subList.stream()
                    .map(Weather::getCountryApiId)
                    .collect(Collectors.joining(","));

            try {
                String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                        .queryParam("id", ids)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .toUriString();

                String response = restTemplate.getForObject(url, String.class);
                JsonNode root = objectMapper.readTree(response);
                JsonNode listNode = root.path("list");

                if (listNode.isArray()) {
                    listNode.forEach(node -> {
                        String apiId = node.path("id").asText();
                        Double temperature = node.path("main").path("temp").asDouble();
                        String conditionCode = node.path("weather").get(0).path("id").asText();

                        List<Weather> weatherEntities = weatherRepository.findByCountryApiId(apiId);
                        if (!weatherEntities.isEmpty()) {
                            weatherEntities.forEach(weather -> {
                                weather.setConditionCode(conditionCode);
                                weather.setTemperature(temperature);
                            });
                        } else {
                            log.warn("해당 Country API ID의 데이터가 없습니다: {}", apiId);
                        }
                    });
                }
            } catch (Exception e) {
                log.error("날씨 데이터 처리 중 예외 발생 {}", e.getMessage(), e);
                throw new RuntimeException("날씨 데이터 처리 실패 " + e.getMessage(), e);
            }
        }
    }

}