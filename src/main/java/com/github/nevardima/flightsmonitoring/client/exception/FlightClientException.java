package com.github.nevardima.flightsmonitoring.client.exception;

import com.github.nevardima.flightsmonitoring.client.dto.ValidationErrorDto;

import java.util.List;

/**
 * A {@link RuntimeException} that is thrown in case of an flight monitoring failures.
 */
public class FlightClientException extends RuntimeException{
    public FlightClientException(String message) {
        super(message);
    }

    public FlightClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FlightClientException(String message, List<ValidationErrorDto> errors) {
        super(message);
        this.validationErrorDtos = errors;
    }

    private List<ValidationErrorDto> validationErrorDtos;
}
