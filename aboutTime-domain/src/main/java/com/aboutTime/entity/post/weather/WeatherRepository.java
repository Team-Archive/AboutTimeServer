package com.aboutTime.entity.post.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByCity(String city);

    List<Weather> findByCountryApiId(String countryApiId);

}