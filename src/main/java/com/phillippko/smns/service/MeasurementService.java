package com.phillippko.smns.service;

import com.phillippko.smns.domain.City;
import com.phillippko.smns.domain.Measurement;
import com.phillippko.smns.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"city"})
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final Environment environment;
    private final CacheManager cacheManager;
    private final CityService cityService;

    public void addMeasurement(BigDecimal latitude, BigDecimal longitude, BigDecimal temperature) {
        Measurement measurement = Measurement
                .builder()
                .latitude(latitude)
                .longitude(longitude)
                .temperature(temperature)
                .city(getCityByLatLng(latitude, longitude))
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



    @Cacheable
    public City getCityByLatLng(BigDecimal latitude, BigDecimal longitude) {
        String geocodingApiUrl = environment.getProperty("geocodingUrl");
        String requestUrl;
        if(geocodingApiUrl != null) {
            String apiKey = environment.getProperty("geocoding.apiKey");
            requestUrl = String.format(geocodingApiUrl, latitude, longitude, apiKey);
        }
            else {
            System.out.println("No geocoding URL was present. Check application.properties");
            return null;
        }
        return null; //TODO
//        CityDto cityDto = Feign.builder()
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .logger(new Slf4jLogger(CityDto.class))
//                .logLevel(Logger.Level.FULL)
//                .target(CityDto.class, requestUrl);
//        return cityService.newCity(cityDto.cityName, latitude, longitude);
    }

}
