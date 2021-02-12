package com.phillippko.smns.repository;

import com.phillippko.smns.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    City findFirstByName(String cityName);
}
