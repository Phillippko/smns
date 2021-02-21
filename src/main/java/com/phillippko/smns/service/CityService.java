package com.phillippko.smns.service;

import com.phillippko.smns.client.CityClient;
import com.phillippko.smns.domain.City;
import com.phillippko.smns.dto.CityDto;
import com.phillippko.smns.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = {"city"})
public class CityService {

    private final CityRepository cityRepository;
    private final Environment environment;
    private final CityClient proxy;

    public City findCityByName(String cityName) {
        return cityRepository.findFirstByName(cityName);
    }


    @Cacheable
    public City getCityByLatLng(BigDecimal latitude, BigDecimal longitude) {
        String apiKey = environment.getProperty("smns.applicationApi");
        CityDto cityDto = proxy.getCity(latitude, longitude, apiKey);
        //        String geocodingApiUrl = environment.getProperty("smns.geocodingUrl");
//        String requestUrl;
//        if(geocodingApiUrl != null) {
//            String apiKey = environment.getProperty("smns.applicationApi");
//            requestUrl = String.format(geocodingApiUrl, latitude, longitude, apiKey);
//        }
//        else {
//            System.out.println("No geocoding URL was present. Check application.properties");
//            return null;
//        }
//           CityClient cityClient = Feign.builder()
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .logger(new Slf4jLogger(CityClient.class))
//                .logLevel(Logger.Level.FULL)
//                .target(CityClient.class, requestUrl);

        return cityOf(cityDto.cityName);
    }

    private City cityOf(String cityName) {
        City city = findCityByName(cityName);
        if(city == null)
        {
            city = new City(cityName);
            cityRepository.save(city);
        }
        return city;
    }

}
