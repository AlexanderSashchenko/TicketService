package com.fintechband.ticketservice.service;

import com.fintechband.ticketservice.model.Ticket;

public interface TicketService {
    Ticket add(Ticket ticket);

    Ticket get(Long id);
}
