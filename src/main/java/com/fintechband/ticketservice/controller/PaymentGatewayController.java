package com.fintechband.ticketservice.controller;

import com.fintechband.ticketservice.service.PaymentGatewayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentGatewayController {

    private final PaymentGatewayService paymentGatewayService;

    public PaymentGatewayController(PaymentGatewayService paymentGatewayService) {
        this.paymentGatewayService = paymentGatewayService;
    }

    @GetMapping("/{ticketId}")
    public String getForPayment(@PathVariable("ticketId") Long id) {
        return paymentGatewayService.processPayment(id);
    }
}
