package com.phillippko.smns.controller;

import com.phillippko.smns.domain.Measurement;
import com.phillippko.smns.dto.MeasurementDto;
import com.phillippko.smns.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MeasurementController {

    private final MeasurementService measurementService;
    private final Environment environment;

    @PostMapping("measurements")
    void postInput(@Valid @RequestBody MeasurementDto measurementDto) {
        measurementService.addMeasurement(measurementDto.latitude, measurementDto.longitude, measurementDto.temperature);
    }

    @GetMapping("measurements")
    List<Measurement> getLastN(@RequestParam(defaultValue = "0") int n,
                               @RequestParam(required = false) String cityName) {
        if(n == 0) {
            n = Integer.parseInt(environment.getProperty("defaultCount","10"));
        }
        return cityName == null ?
                measurementService.getLastN(n) :
                measurementService.getLastN(n, cityName);
    }

}
