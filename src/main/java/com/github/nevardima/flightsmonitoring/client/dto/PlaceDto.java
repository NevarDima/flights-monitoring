package com.github.nevardima.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.nevardima.flightsmonitoring.service.impl.FlightPriceServiceImpl;
import lombok.Data;

/**
 * Data transfer object using in {@link FlightPriceServiceImpl}.
 */
@Data
public class PlaceDto {
    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("IataCode")
    private String iataCode;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkyscannerCode")
    private String skyscannerCode;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;
}
