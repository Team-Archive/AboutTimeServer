package com.aboutTime.entity.post.weather;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "weather_icon")
@AllArgsConstructor
public class WeatherIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_icon_id")
    private Long id;

    //WeatherData의 iconName
    @Column(name = "code")
    private String code;

    @Column(name = "icon_url")
    private String url;

}
