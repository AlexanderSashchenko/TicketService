package com.fintechband.ticketservice.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class TicketRequestDto {
    @NotEmpty
    private String routeNumber;

    @NotEmpty
    private String departureDateTime;

}
