package com.fintechband.ticketservice.controller;

import com.fintechband.ticketservice.model.Ticket;
import com.fintechband.ticketservice.model.dto.TicketRequestDto;
import com.fintechband.ticketservice.service.TicketService;

import java.time.LocalDateTime;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
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
    public String add(@RequestBody @Valid TicketRequestDto ticketRequestDto,
                      BindingResult bindingResult) {
        Ticket ticket = new Ticket();
        try {
            ticket.setRouteNumber(ticketRequestDto.getRouteNumber());
            ticket.setDepartureDateTime(LocalDateTime.parse(ticketRequestDto
                    .getDepartureDateTime()));
            if (bindingResult.hasErrors()) {
                return "Validation error. Please enter correct data.";
            }
            ticketService.add(ticket);
            return ticket.getId().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping
    @RequestMapping("/{ticketId}")
    public String get(@PathVariable("ticketId") Long id) {
        return ticketService.get(id).getStatus().getStatusName();
    }
}
