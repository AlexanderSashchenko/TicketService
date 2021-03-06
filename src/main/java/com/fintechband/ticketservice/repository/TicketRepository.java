package com.fintechband.ticketservice.repository;

import com.fintechband.ticketservice.model.Status;
import com.fintechband.ticketservice.model.Ticket;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findTopByStatus(Status status);
}
