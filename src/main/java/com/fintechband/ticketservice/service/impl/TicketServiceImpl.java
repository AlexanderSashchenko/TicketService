package com.fintechband.ticketservice.service.impl;

import com.fintechband.ticketservice.model.Status;
import com.fintechband.ticketservice.model.Ticket;
import com.fintechband.ticketservice.repository.StatusRepository;
import com.fintechband.ticketservice.repository.TicketRepository;
import com.fintechband.ticketservice.service.TicketService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String INITIAL_STATUS_NAME = "NEW";
    private final TicketRepository ticketRepository;
    private final StatusRepository statusRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             StatusRepository statusRepository) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Status status = statusRepository.findByStatusName(INITIAL_STATUS_NAME);
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket get(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            throw new RuntimeException("Failed to find ticket with id" + id);
        }
    }
}
