package com.phillippko.smns.service;

import com.phillippko.smns.domain.Measurement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MeasurementServiceTest {

    private static final String SPB_LAT = "59.95";
    private static final String SPB_LNG = "30.31";
    private static final String NY_LNG = "40.71";
    private static final String NY_LAT = "-74";
    private static final String LA_LAT = "34.05" ;
    private static final String LA_LNG = "-118.24";

    @Autowired
    private MeasurementService measurementService;

    @BeforeAll
    public static void addThreeValidOneInvalid(@Autowired MeasurementService measurementService) {

        measurementService.addMeasurement(SPB_LAT, SPB_LNG, 10);
        measurementService.addMeasurement(NY_LAT, NY_LNG, 0);
        measurementService.addMeasurement(LA_LAT, LA_LNG, -10);
        measurementService.addMeasurement("-190", LA_LNG, -10);

    }

    @Test
    void getLastNTest() {
        assertEquals(measurementService.getLastN(2).size(),2);
    }

    @Test
    void getFilteredByCityTest() {
        assertEquals(measurementService.getFilteredByCity("Санкт-Петербург").size(), 1);

    }

    @Test
    void addMeasurementTest() {
        List<Measurement> measurementList = measurementService.getLastN(4);
        assertEquals(measurementList.size(), 3);
    }



}