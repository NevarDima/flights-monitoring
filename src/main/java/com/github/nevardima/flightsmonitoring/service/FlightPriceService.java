package com.github.nevardima.flightsmonitoring.service;

import com.github.nevardima.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.nevardima.flightsmonitoring.repository.entity.Subscription;

/**
 * Service, for getting details based on {@link Subscription} object.
 */
public interface FlightPriceService {
    /**
     * Finds minPrice based on {@link Subscription}.
     *
     * @param flightPricesDto provided {@link FlightPricesDto} object.
     * @return
     */
    Integer findMinPrice(FlightPricesDto flightPricesDto);

    /**
     * Finds all the flight data related to {@link Subscription} object.
     *
     * @param subscription provided {@link Subscription} object
     * @return {@link FlightPricesDto} with all the data related to flight specific in {@link Subscription}.
     */
    FlightPricesDto findFlightPrice(Subscription subscription);
}
