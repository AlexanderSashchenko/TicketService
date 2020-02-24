package com.fintechband.ticketservice.model.dto;

import lombok.Getter;

@Getter
public class TicketRequestDto {
    private String routeNumber;

    private String departureDateTime;
}
