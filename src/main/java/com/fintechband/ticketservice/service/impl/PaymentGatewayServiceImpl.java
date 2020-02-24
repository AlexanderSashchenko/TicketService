package com.fintechband.ticketservice.service.impl;

import com.fintechband.ticketservice.service.PaymentGatewayService;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private static final String IN_PROGRESS_STATUS_NAME = "IN_PROGRESS";
    private static final String ERROR_STATUS_NAME = "ERROR";
    private static final String SUCCESS_STATUS_NAME = "SUCCESS";

    @Override
    public String processPayment(Long id) {
        Random random = new Random();
        String[] processedStatuses = new String[] {
                IN_PROGRESS_STATUS_NAME,
                ERROR_STATUS_NAME,
                SUCCESS_STATUS_NAME
        };
        int index = random.nextInt(processedStatuses.length);
        return processedStatuses[index];
    }
}
