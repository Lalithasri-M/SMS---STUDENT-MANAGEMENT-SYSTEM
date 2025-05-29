package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.PaymentDetDto;
import com.studentregistration.studentregistration.service.PaymentDetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentDetService paymentDetService;

    @PostMapping("/savePaymentDetails")
    public ResponseEntity<PaymentDetDto> createPaymentDetails(@RequestBody PaymentDetDto paymentDetDto) {
        PaymentDetDto savePaymentDetails = paymentDetService.createPaymentDetDto(paymentDetDto);
        return new ResponseEntity<>(savePaymentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getPaymentDetailById/{paymentId}")
    public ResponseEntity<PaymentDetDto> getPaymentDetailById(@PathVariable("paymentId") Long paymentId) {
        PaymentDetDto paymentDetDto = paymentDetService.getPaymentDetailById(paymentId);
        return new ResponseEntity<>(paymentDetDto,HttpStatus.OK);
    }

    @GetMapping("/getAllPaymentDetails")
    public ResponseEntity<List<PaymentDetDto>> getAllPaymentDetails() {
        List<PaymentDetDto> paymentDetails = paymentDetService.getAllPaymentDetails();
        return new ResponseEntity<>(paymentDetails,HttpStatus.OK);
    }

    @PutMapping("/updatePaymentDetail/{paymentId}")
    public ResponseEntity<PaymentDetDto> updatePaymentDetail(@PathVariable("paymentId") Long paymentId, @RequestBody PaymentDetDto updatePaymentDetDto) {
        PaymentDetDto paymentDetDto = paymentDetService.updatePaymentDetail(paymentId,updatePaymentDetDto);
        return new ResponseEntity<>(paymentDetDto,HttpStatus.OK);
    }

    @DeleteMapping("/deletePaymentDetailById/{paymentId}")
    public ResponseEntity<String> deletePaymentDetailById(@PathVariable("paymentId") Long paymentId) {
        paymentDetService.deletePaymentDetailById(paymentId);
        return ResponseEntity.ok("Payment Details Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllPaymentDetails")
    public ResponseEntity<String> deleteAllPaymentDetails() {
        paymentDetService.deleteAllPaymentDetails();
        return ResponseEntity.ok("Payment Details Deleted Successfully!...");
    }
}
