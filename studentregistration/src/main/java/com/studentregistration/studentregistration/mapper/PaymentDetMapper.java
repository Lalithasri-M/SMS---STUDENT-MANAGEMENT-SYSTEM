package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.PaymentDetDto;
import com.studentregistration.studentregistration.model.PaymentDetails;

public class PaymentDetMapper {

    public static PaymentDetDto mapToPaymentDetDto(PaymentDetails paymentDetails) {

        Long studentId = paymentDetails.getStudent() != null ? paymentDetails.getStudent().getStudentId() : null;
        Long courseId = paymentDetails.getCourse() != null ? paymentDetails.getCourse().getCourseId() : null;

        return new PaymentDetDto(
                paymentDetails.getPaymentId(),
                paymentDetails.getPaymentDate(),
                paymentDetails.getPaidAmount(),
                paymentDetails.getPaymentMethod(),
                paymentDetails.getRegisterNo(),
                studentId,
                courseId,
                paymentDetails.getStudent(),
                paymentDetails.getCourse()
        );
    }

    public static PaymentDetails mapToPaymentDetails(PaymentDetDto paymentDetDto) {
        return new PaymentDetails(
                paymentDetDto.getPaymentId(),
                paymentDetDto.getPaymentDate(),
                paymentDetDto.getPaidAmount(),
                paymentDetDto.getPaymentMethod(),
                paymentDetDto.getRegisterNo(),
                null,
                null
        );
    }
}
