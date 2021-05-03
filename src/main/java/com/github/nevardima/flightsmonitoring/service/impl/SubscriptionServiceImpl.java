package com.github.nevardima.flightsmonitoring.service.impl;

import com.github.nevardima.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.nevardima.flightsmonitoring.repository.SubscriptionRepository;
import com.github.nevardima.flightsmonitoring.repository.entity.Subscription;
import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionCreateDto;
import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionUpdateDto;
import com.github.nevardima.flightsmonitoring.service.EmailNotifierService;
import com.github.nevardima.flightsmonitoring.service.FlightPriceService;
import com.github.nevardima.flightsmonitoring.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto create(SubscriptionCreateDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepository.findOne(Example.of(subscription));

        if (one.isPresent()) {
            log.info("The same subscription has been found for Subscription={}", subscription);
            Subscription fromDatabase = one.get();
            FlightPricesDto flightPriceResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPriceResponse));
            return toDto(fromDatabase, flightPriceResponse);
        } else {
            FlightPricesDto flightPriceResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPriceResponse));
            Subscription saved = subscriptionRepository.save(subscription);
            log.info("Added new subscription={}", saved);
            emailNotifierService.notifyAddingSubscription(subscription);
            return toDto(saved, flightPriceResponse);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubscriptionDto> findByEmail(String email) {
        return subscriptionRepository.findByEmail(email).stream()
                .map(subscription -> {
                    FlightPricesDto flightPriceResponse = flightPriceService.findFlightPrice(subscription);
                    if (subscription.getMinPrice() != flightPriceService.findMinPrice(flightPriceResponse)) {
                        subscription.setMinPrice(flightPriceService.findMinPrice(flightPriceResponse));
                        subscriptionRepository.save(subscription);
                    }
                    return toDto(subscription, flightPriceResponse);
                })
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto) {
        Subscription subscription = subscriptionRepository.getOne(subscriptionId);
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setLocale(dto.getLocale());
        subscription.setCurrency(dto.getCurrency());
        subscription.setCountry(dto.getCountry());
        subscription.setEmail(dto.getEmail());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());

        FlightPricesDto flightPriceResponse = flightPriceService.findFlightPrice(subscription);
        subscription.setMinPrice(flightPriceService.findMinPrice(flightPriceResponse));
        return toDto(subscriptionRepository.save(subscription), flightPriceResponse);
    }

    private Subscription toEntity(SubscriptionCreateDto dto) {
        Subscription subscription = new Subscription();
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setEmail(dto.getEmail());

        return subscription;
    }

    private SubscriptionDto toDto(Subscription entity, FlightPricesDto response) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setEmail(entity.getEmail());
        dto.setCountry(entity.getCountry());
        dto.setCurrency(entity.getCurrency());
        dto.setLocale(entity.getLocale());
        dto.setOriginPlace(entity.getOriginPlace());
        dto.setDestinationPlace(entity.getDestinationPlace());
        dto.setOutboundPartialDate(entity.getOutboundPartialDate());
        dto.setInboundPartialDate(entity.getInboundPartialDate());
        dto.setMinPrice(entity.getMinPrice());
        dto.setId(entity.getId());
        dto.setFlightPricesDto(response);
        return dto;
    }
}
