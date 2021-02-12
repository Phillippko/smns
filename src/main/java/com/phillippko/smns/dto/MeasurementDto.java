package com.phillippko.smns.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class MeasurementDto {
    private static final int MIN_TEMP = -273;
    private static final int MAX_TEMP = 1000;
    private static final int LAT = 90;
    private static final int LNG = 180;

    @Min(value = -LAT)
    @Max(value = LAT)
    public BigDecimal latitude;
    @Min(value = -LNG)
    @Max(value = LNG)
    public BigDecimal longitude;
    @Min(value = MIN_TEMP)
    @Max(value = MAX_TEMP)
    public BigDecimal temperature;
}
