package com.github.nevardima.flightsmonitoring.rest.controller;

import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionCreateDto;
import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.nevardima.flightsmonitoring.rest.dto.SubscriptionUpdateDto;
import com.github.nevardima.flightsmonitoring.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * {@link Controller} to handle Subscriptions.
 */
@Api(value = "Operations with Subscriptions", tags = "Subscription Controller")
@RequestMapping(SubscriptionController.SUBSCRIPTION_CONTROLLER_EP)
@Controller
public class SubscriptionController {

    public static final String SUBSCRIPTION_CONTROLLER_EP = "/subscription";

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation("Create new subscription based on SubscriptionDto")
    @PostMapping
    public @ResponseBody
    SubscriptionDto create(@RequestBody @Valid SubscriptionCreateDto dto) {
        return subscriptionService.create(dto);
    }

    @ApiOperation("Finds all subscriptions based on email")
    @GetMapping("/{email}")
    public @ResponseBody
    List<SubscriptionDto> findByEmail(@PathVariable final String email) {
        return subscriptionService.findByEmail(email);
    }

    @ApiOperation("Updates subscription based on it ID")
    @PutMapping("/{id}")
    public SubscriptionDto update(@PathVariable final Long id,
                                  @RequestBody @Valid SubscriptionUpdateDto dto) {
        return subscriptionService.update(id, dto);
    }

    @ApiOperation("Deletes subscription based on it ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        subscriptionService.delete(id);
    }
}
