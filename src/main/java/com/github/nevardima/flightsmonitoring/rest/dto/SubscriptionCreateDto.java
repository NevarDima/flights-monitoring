package com.github.nevardima.flightsmonitoring.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nevardima.flightsmonitoring.repository.entity.Subscription;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data transfer object to create a {@link Subscription} object.
 */
@Data
public class SubscriptionCreateDto {
    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email", example = "test@test.com")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Country Code", example = "UA")
    private String country;

    @NotNull
    @ApiModelProperty(value = "Currency Code", example = "UAH")
    private String currency;

    @NotNull
    @ApiModelProperty(value = "Locale", example = "ru-RU")
    private String locale;

    @NotNull
    @ApiModelProperty(value = "Code of the origin place", example = "HRK-sky")
    private String originPlace;

    @NotNull
    @ApiModelProperty(value = "Code of the destination place", example = "KBP-sky")
    private String destinationPlace;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date front", example = "2019-12-18")
    private LocalDate outboundPartialDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date back", example = "2019-12-25")
    private LocalDate inboundPartialDate;
}
