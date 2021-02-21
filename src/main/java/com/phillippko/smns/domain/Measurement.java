package com.phillippko.smns.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Measurement {


    private final BigDecimal latitude;
    private final BigDecimal longitude;
    @ManyToOne
    private City city;
    private final BigDecimal temperature;
    @Id
    @GeneratedValue
    private final UUID id;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
