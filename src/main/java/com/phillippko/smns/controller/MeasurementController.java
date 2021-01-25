package com.phillippko.smns.controller;

import com.phillippko.smns.domain.Measurement;
import com.phillippko.smns.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/input")
    void postInput(@RequestParam String latitude,
                   @RequestParam String longitude,
                   @RequestParam double temperature) {
        measurementService.addMeasurement(latitude, longitude, temperature);
    }

    @GetMapping("get")
    List<Measurement> getTenMeasurements() {
        return measurementService.getLastN(10);
    }

    @GetMapping("get-filtered")
    List<Measurement> getFilteredByCity(
            @RequestParam String cityName) {
        return measurementService.getFilteredByCity(cityName);
    }
}
