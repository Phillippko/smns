package com.phillippko.smns.service;

import com.phillippko.smns.domain.City;
import com.phillippko.smns.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public City findCityByName(String cityName) {
        return cityRepository.findFirstByName(cityName);
    }
}
