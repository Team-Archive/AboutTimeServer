package com.aboutTime.entity.post.weather;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "condition_code")
    private String conditionCode;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "country_api_id")
    private String countryApiId;

    @Builder
    public Weather(String city, String countryCode, String conditionCode, Double temperature, String countryApiId) {
        this.city = city;
        this.countryCode = countryCode;
        this.conditionCode = conditionCode;
        this.temperature = temperature;
        this.countryApiId = countryApiId;
    }

}

