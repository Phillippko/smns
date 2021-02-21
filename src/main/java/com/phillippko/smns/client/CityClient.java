package com.phillippko.smns.client;

import com.phillippko.smns.dto.CityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@FeignClient(name = "stores", url = "https://revgeocode.search.hereapi.com")
public interface CityClient {
    @RequestMapping("/revgeocode?at={latitude},{longitude}&apiKey={apiKey}&types=city")
    CityDto getCity(@PathVariable BigDecimal latitude, @PathVariable BigDecimal longitude, @PathVariable String apiKey);
}
