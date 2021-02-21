package com.phillippko.smns.repository;

import com.phillippko.smns.domain.City;
import com.phillippko.smns.domain.Measurement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long> {
    List<Measurement> findAllByCity(City city, Pageable pageOfNMeasurements);
}
