package com.fintechband.ticketservice.controller;

import com.fintechband.ticketservice.model.Ticket;
import com.fintechband.ticketservice.model.dto.TicketRequestDto;
import com.fintechband.ticketservice.service.TicketService;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Long add(@RequestBody TicketRequestDto ticketRequestDto) {
        Ticket ticket = new Ticket();
        ticket.setRouteNumber(ticketRequestDto.getRouteNumber());
        ticket.setDepartureDateTime(LocalDateTime.parse(ticketRequestDto.getDepartureDateTime()));
        ticketService.add(ticket);
        return ticket.getId();
    }

    @GetMapping
    @RequestMapping("/{ticketId}")
    public String get(@PathVariable("ticketId") Long id) {
        return ticketService.get(id).getStatus().getStatusName();
    }
}
