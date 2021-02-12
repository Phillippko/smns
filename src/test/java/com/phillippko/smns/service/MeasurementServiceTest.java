package com.phillippko.smns.service;

/*
@AllArgsConstructor
@SpringBootTest
@RequiredArgsConstructor
class MeasurementServiceTest

{
    private static final BigDecimal SPB_LAT = BigDecimal.valueOf(59.95);
    private static final BigDecimal SPB_LNG = BigDecimal.valueOf(30.31);
    private static final BigDecimal NY_LNG = BigDecimal.valueOf(40.71);
    private static final BigDecimal NY_LAT = BigDecimal.valueOf(-74);
    private static final BigDecimal LA_LAT = BigDecimal.valueOf(34.05);
    private static final BigDecimal LA_LNG = BigDecimal.valueOf(-118.24);
    private static final BigDecimal INCOR_LAT = BigDecimal.valueOf(-190);

    private final MeasurementService measurementService;

    @BeforeAll
    public static void addThreeValidOneInvalid(@Autowired MeasurementService measurementService) {

        measurementService.addMeasurement(SPB_LAT, SPB_LNG, BigDecimal.valueOf(10));
        measurementService.addMeasurement(NY_LAT, NY_LNG, BigDecimal.valueOf(0));
        measurementService.addMeasurement(LA_LAT, LA_LNG, BigDecimal.valueOf(-10));
        measurementService.addMeasurement(INCOR_LAT, LA_LNG, BigDecimal.valueOf(-10));

    }

    @Test
    void getLastNTest() {
        assertEquals(measurementService.getLastN(2).size(),2);
    }

    @Test
    void getFilteredByCityTest() {
        assertEquals(measurementService.getLastN(10,"Санкт-Петербург").size(), 1);

    }

    @Test
    void addMeasurementTest() {
        List<Measurement> measurementList = measurementService.getLastN(4);
        assertEquals(measurementList.size(), 3);
    }



}*/