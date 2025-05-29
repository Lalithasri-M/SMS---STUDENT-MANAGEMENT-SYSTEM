package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.PaymentDetDto;

import java.util.List;

public interface PaymentDetService {
    PaymentDetDto createPaymentDetDto(PaymentDetDto paymentDetDto);

    PaymentDetDto getPaymentDetailById(Long paymentId);

    List<PaymentDetDto> getAllPaymentDetails();

    PaymentDetDto updatePaymentDetail(Long paymentId, PaymentDetDto updatepaymentDetDto);

    void deletePaymentDetailById(Long paymentId);

    void deleteAllPaymentDetails();
}
