package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.PaymentDetDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.PaymentDetMapper;
import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.PaymentDetails;
import com.studentregistration.studentregistration.model.Student;
import com.studentregistration.studentregistration.repository.CourseRepository;
import com.studentregistration.studentregistration.repository.PaymentDetRepository;
import com.studentregistration.studentregistration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentDetServiceImpl implements PaymentDetService{

    @Autowired
    private PaymentDetRepository paymentDetRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PaymentDetDto createPaymentDetDto(PaymentDetDto paymentDetDto) {
        PaymentDetails paymentDetails = PaymentDetMapper.mapToPaymentDetails(paymentDetDto);

        Student student = studentRepository.findById(paymentDetDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        paymentDetails.setStudent(student);

        Course course = courseRepository.findById(paymentDetDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        paymentDetails.setCourse(course);

        PaymentDetails addPaymentDetails = paymentDetRepository.save(paymentDetails);
        return PaymentDetMapper.mapToPaymentDetDto(addPaymentDetails);
    }

    @Override
    public PaymentDetDto getPaymentDetailById(Long paymentId) {
        PaymentDetails paymentDetails = paymentDetRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Details does not exist with the provided Id : "+ paymentId));
        return PaymentDetMapper.mapToPaymentDetDto(paymentDetails);
    }

    @Override
    public List<PaymentDetDto> getAllPaymentDetails() {
        List<PaymentDetails> paymentDetails = paymentDetRepository.findAll();
        return paymentDetails.stream().map((paymentDetail) -> PaymentDetMapper.mapToPaymentDetDto(paymentDetail)).collect(Collectors.toList());
    }

    @Override
    public PaymentDetDto updatePaymentDetail(Long paymentId, PaymentDetDto updatepaymentDetDto) {
        PaymentDetails paymentDetails = paymentDetRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Details does not exist with the provided Id : "+ paymentId));

        paymentDetails.setPaymentDate(updatepaymentDetDto.getPaymentDate());
        paymentDetails.setPaidAmount(updatepaymentDetDto.getPaidAmount());
        paymentDetails.setPaymentMethod(updatepaymentDetDto.getPaymentMethod());
        paymentDetails.setRegisterNo(updatepaymentDetDto.getRegisterNo());

        Student student = studentRepository.findById(updatepaymentDetDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + updatepaymentDetDto.getStudentId()));
        paymentDetails.setStudent(student);

        Course course = courseRepository.findById(updatepaymentDetDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + updatepaymentDetDto.getCourseId()));
        paymentDetails.setCourse(course);

        PaymentDetails updatePaymentDetails = paymentDetRepository.save(paymentDetails);
        return PaymentDetMapper.mapToPaymentDetDto(updatePaymentDetails);
    }

    @Override
    public void deletePaymentDetailById(Long paymentId) {
        PaymentDetails paymentDetails = paymentDetRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Details does not exist with the provided Id : "+ paymentId));

        paymentDetRepository.deleteById(paymentId);
    }

    @Override
    public void deleteAllPaymentDetails() {
        paymentDetRepository.deleteAll();
    }
}
