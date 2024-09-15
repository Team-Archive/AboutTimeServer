package com.aboutTime.entity.post.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findByUpdatedAtBefore(LocalDateTime cutoffTime);
    Optional<Weather> findByCity(String city);

}