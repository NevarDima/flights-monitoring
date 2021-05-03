package com.github.nevardima.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.nevardima.flightsmonitoring.client.service.FlightPricesClient;
import lombok.Data;

import java.util.List;

/**
 * Response object for the Flight Prices.
 */
@Data
public class FlightPricesDto {
    @JsonProperty("Quotes")
    private List<QuoteDto> quotas;

    @JsonProperty("Places")
    private List<PlaceDto> places;

    @JsonProperty("Carrier")
    private List<CarrierDto> carriers;

    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;
}
