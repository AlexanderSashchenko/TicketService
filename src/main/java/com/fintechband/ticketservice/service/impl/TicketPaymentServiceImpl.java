package com.fintechband.ticketservice.service.impl;

import com.fintechband.ticketservice.model.Status;
import com.fintechband.ticketservice.model.Ticket;
import com.fintechband.ticketservice.repository.StatusRepository;
import com.fintechband.ticketservice.repository.TicketRepository;
import com.fintechband.ticketservice.service.TicketPaymentService;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TicketPaymentServiceImpl implements TicketPaymentService {

    private static final String INITIAL_STATUS_NAME = "NEW";
    private static final String IN_PROGRESS_STATUS_NAME = "IN_PROGRESS";
    private static final String BASE_URL = "http://localhost:8080";
    private final StatusRepository statusRepository;
    private final TicketRepository ticketRepository;
    private final WebClient webClient;

    public TicketPaymentServiceImpl(StatusRepository statusRepository,
                                    TicketRepository ticketRepository,
                                    WebClient.Builder webClientBuilder) {
        this.statusRepository = statusRepository;
        this.ticketRepository = ticketRepository;
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Scheduled(initialDelay = 60000L, fixedRate = 60000L)
    private void initPayment() {
        Optional<Ticket> ticket = getForPayment();
        if (ticket.isPresent()) {
            Long id = ticket.get().getId();
            String updatedStatusName = webClient.get()
                    .uri("/payment-gateway/{id}", id)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            updateStatus(id, updatedStatusName);
        } else {
            log.error("No tickets available for payment found");
            throw new RuntimeException();
        }
    }

    private Optional<Ticket> getForPayment() {
        Status statusNew = statusRepository.findByStatusName(INITIAL_STATUS_NAME);
        Status statusInProgress = statusRepository.findByStatusName(IN_PROGRESS_STATUS_NAME);
        Optional<Ticket> ticketNew = ticketRepository.findTopByStatus(statusNew);
        Optional<Ticket> ticketInProgress = ticketRepository.findTopByStatus(statusInProgress);
        return ticketNew.isPresent() ? ticketNew : ticketInProgress;
    }

    private void updateStatus(Long ticketId, String updatedStatusName) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        Status updatedStatus = statusRepository.findByStatusName(updatedStatusName);
        if (ticket.isPresent()) {
            Ticket updatedTicket = ticket.get();
            updatedTicket.setStatus(updatedStatus);
            ticketRepository.save(updatedTicket);
        } else {
            log.error("Failed to update ticket status");
            throw new RuntimeException();
        }
    }
}
