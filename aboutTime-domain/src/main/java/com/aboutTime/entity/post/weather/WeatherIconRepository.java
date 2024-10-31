package com.aboutTime.entity.post.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherIconRepository extends JpaRepository<WeatherIcon, Long> {

    Optional<WeatherIcon> findByCode(String code);
}
