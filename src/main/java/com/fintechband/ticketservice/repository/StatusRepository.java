package com.fintechband.ticketservice.repository;

import com.fintechband.ticketservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String statusName);
}
