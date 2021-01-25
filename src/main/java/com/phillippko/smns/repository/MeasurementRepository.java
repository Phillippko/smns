package com.phillippko.smns.repository;

import com.phillippko.smns.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findAllByCity(String city);
}
