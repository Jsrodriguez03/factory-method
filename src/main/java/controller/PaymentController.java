package main.java.controller;

import main.java.domain.*;
import main.java.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServices service;

    @GetMapping("/types")
    public ResponseEntity<List<String>> getPaymentTypes() {
        return ResponseEntity.ok(PaymentType.getAllPaymentTypes());
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payPayment(@RequestParam PaymentType paymentType, @RequestParam double amount){
        double newFinalAmount = service.senderPayment(paymentType, amount);
        return ResponseEntity.ok(newFinalAmount);
    }

}
