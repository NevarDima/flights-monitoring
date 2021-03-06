package com.github.nevardima.flightsmonitoring.client.service;

import com.github.nevardima.flightsmonitoring.client.dto.PlaceDto;
import com.github.nevardima.flightsmonitoring.client.dto.PlacesDto;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;

/**
 * SkyScanner client.
 */
public interface PlacesClient {
    /**
     * Get a list of places that match a query string based on arguments.
     *
     * @param query the code of the city.
     * @param country the code of the country.
     * @param currency the code of the currency.
     * @param locale the code of the locale.
     * @return the collection of the {@link PlaceDto} objects.
     */
    List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale)
            throws IOException, UnirestException;
}
