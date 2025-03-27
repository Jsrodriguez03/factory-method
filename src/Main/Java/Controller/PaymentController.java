package Main.Java.Controller;

import Main.Java.Services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServices service;

    @GetMapping("/pay")
    public ResponseEntity<?> payPayment(@RequestParam String paymentType, @RequestParam double amount){

        return ResponseEntity.ok(service.payPayment(paymentType, amount));

    }

}
