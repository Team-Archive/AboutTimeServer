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

    // API의 응답값으로 주는 고유 코드
    @Column(name = "condition_code")
    private String conditionCode;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "hour")
    private int hour;

    @Builder
    public Weather(String city, String conditionCode, double temperature, int hour) {
        this.city = city;
        this.conditionCode = conditionCode;
        this.temperature = temperature;
        this.hour = hour;
    }

}

