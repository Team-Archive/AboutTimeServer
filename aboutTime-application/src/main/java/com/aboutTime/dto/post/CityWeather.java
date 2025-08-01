package com.aboutTime.dto.post;

import lombok.Data;

@Data
public class CityWeather {

    private Long id;
    private String name;
    private Integer weatherId;
    private Double temperature;

}
