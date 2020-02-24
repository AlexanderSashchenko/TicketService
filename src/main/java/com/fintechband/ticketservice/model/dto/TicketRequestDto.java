package com.fintechband.ticketservice.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class TicketRequestDto {
    @NotNull
    @NotEmpty
    private String routeNumber;

    @NotNull
    @NotEmpty
    private String departureDateTime;
}
