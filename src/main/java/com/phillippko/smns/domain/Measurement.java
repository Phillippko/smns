package com.phillippko.smns.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


@Entity
@Data
public class Measurement {

    private static final double MIN_TEMP = -273;
    private static final double MAX_TEMP = 1000;
    private static final double MIN_LAT = -180;
    private static final double MAX_LAT = 180;
    private static final double MIN_LONGIT = -180;
    private static final double MAX_LONGIT = 180;

    private static final String API_KEY = "f9HRCEC-96MTC6uuWuZBpoFcujFBnNmed_47-bt6W-0";
    private static final String GEOCODING_URL =
            "https://revgeocode.search.hereapi.com/v1/revgeocode?at=%s,%s&apiKey=%s&types=city";

    String latitude;
    String longitude;
    String city;
    double temperature;

    @Id
    @GeneratedValue
    final Long id = null;

    public Measurement() {
    }

    static public Measurement of(String latitude, String longitude, double temperature){
        Measurement measurement = new Measurement();
        measurement.latitude = latitude;
        measurement.longitude = longitude;
        measurement.temperature = temperature;
        return measurement;
    }

    public static boolean validate(Measurement measurement) {
        double latitude = Double.parseDouble(measurement.latitude);
        double longitude = Double.parseDouble(measurement.longitude);
        return isBetween(MIN_TEMP, MAX_TEMP, measurement.temperature) &&
                isBetween(MIN_LAT, MAX_LAT, latitude) &&
                isBetween(MIN_LONGIT, MAX_LONGIT, longitude);
    }
    private static boolean isBetween(double first, double second, double checking){
        return
                checking >= first && checking <= second;
    }

    public void setCity() {
        HttpClient http = HttpClient.newHttpClient();
        String requestUri = String.format(GEOCODING_URL, latitude, longitude, API_KEY);
        HttpRequest geocodingRequest = getRequest(requestUri);
        HttpResponse<String> geocodingResponse = getResponse(http, geocodingRequest);
        if (geocodingResponse == null)
            return;
        city = getCityFromResponse(geocodingResponse.body());
    }

    private HttpRequest getRequest(String requestUri) {
        return HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();
    }

    private HttpResponse<String> getResponse(HttpClient http, HttpRequest geocodingRequest) {
        HttpResponse<String> geocodingResponse;
        try {
            geocodingResponse = http.send(geocodingRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
        return geocodingResponse;
    }

    private String getCityFromResponse(String body) {
        try {
            JsonObject json = (new Gson().fromJson(body, JsonObject.class));
            JsonArray itemsArray = json.getAsJsonArray("items");
            JsonObject address = itemsArray.get(0).getAsJsonObject().get("address").getAsJsonObject();
            return address.get("city").getAsString();
        } catch (Exception e) {
            return null;
        }

    }


}
