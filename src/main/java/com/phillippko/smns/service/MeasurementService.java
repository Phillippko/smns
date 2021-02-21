package com.phillippko.smns.service;

import com.phillippko.smns.domain.City;
import com.phillippko.smns.domain.Measurement;
import com.phillippko.smns.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final CityService cityService;

    public void addMeasurement(BigDecimal latitude, BigDecimal longitude, BigDecimal temperature) {
        Measurement measurement = Measurement
                .builder()
                .latitude(latitude)
                .longitude(longitude)
                .temperature(temperature)
                .city(cityService.getCityByLatLng(latitude, longitude))
                .build();
        measurementRepository.save(measurement);
    }


    public List<Measurement> getLastN(int n) {
        Pageable pageOfNMeasurements = PageRequest.of(0, n);
        return measurementRepository.findAll(pageOfNMeasurements).getContent();
}

    public List<Measurement> getLastN(int n, String cityName) {
        Pageable pageOfNMeasurements = PageRequest.of(0, n);
        City city = cityService.findCityByName(cityName);
        return measurementRepository.findAllByCity(city, pageOfNMeasurements);
    }




}
