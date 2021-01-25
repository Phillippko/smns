package com.phillippko.smns.service;

import com.phillippko.smns.domain.Measurement;
import com.phillippko.smns.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    public void addMeasurement(String latitude, String longitude, double temperature){
        Measurement measurement = Measurement.of(latitude, longitude, temperature);
        if(Measurement.validate(measurement)){
            measurement.setCity();
            measurementRepository.save(measurement);
        }
    }

    public List<Measurement> getLastN(int n) {
        List<Measurement> measurements = measurementRepository.findAll();
        return measurements.subList(Math.max(measurements.size() - n, 0), measurements.size());
    }

    public List<Measurement> getFilteredByCity(String cityName) {
        return measurementRepository.findAllByCity(cityName);

    }
}
