package com.github.nevardima.flightsmonitoring.service.impl;

import com.github.nevardima.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.nevardima.flightsmonitoring.client.service.FlightPricesClient;
import com.github.nevardima.flightsmonitoring.repository.entity.Subscription;
import com.github.nevardima.flightsmonitoring.service.FlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class FlightPriceServiceImpl implements FlightPriceService {
    @Autowired
    private FlightPricesClient flightPricesClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer findMinPrice(FlightPricesDto flightPricesDto) {
        return flightPricesDto.getQuotas().get(0).getMinPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightPricesDto findFlightPrice(Subscription subscription) {
        if (subscription.getInboundPartialDate() == null) {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(),
                            subscription.getOutboundPartialDate().toString());
        } else {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(),
                            subscription.getOutboundPartialDate().toString(), subscription.getInboundPartialDate().toString());
        }
    }
}
