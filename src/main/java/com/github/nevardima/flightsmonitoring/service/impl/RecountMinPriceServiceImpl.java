package com.github.nevardima.flightsmonitoring.service.impl;

import com.github.nevardima.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.nevardima.flightsmonitoring.repository.SubscriptionRepository;
import com.github.nevardima.flightsmonitoring.service.EmailNotifierService;
import com.github.nevardima.flightsmonitoring.service.FlightPriceService;
import com.github.nevardima.flightsmonitoring.service.RecountMinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * {@inheritDoc}
 */
@Service
public class RecountMinPriceServiceImpl implements RecountMinPriceService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    //todo add async
    /**
     * {@inheritDoc}
     */
    @Override
    public void recount() {
        subscriptionRepository.findAll().forEach(subscription -> {
            if(subscription.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1))) {
                FlightPricesDto flightPricesDto = flightPriceService.findFlightPrice(subscription);
                Integer newNumPrice = flightPriceService.findMinPrice(flightPricesDto);
                if (subscription.getMinPrice() > newNumPrice) {
                    emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newNumPrice);
                    subscription.setMinPrice(newNumPrice);
                    subscriptionRepository.save(subscription);
                }
            } else {
                subscriptionRepository.delete(subscription);
            }
        });
    }
}
