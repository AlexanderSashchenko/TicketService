package com.fintechband.ticketservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Entered route number is empty")
    private String routeNumber;

    @Future(message =
            "Entered date and time is invalid or past. Please use this format: YYYY-mm-ddThh:mm")
    private LocalDateTime departureDateTime;

    @OneToOne
    private Status status;
}
