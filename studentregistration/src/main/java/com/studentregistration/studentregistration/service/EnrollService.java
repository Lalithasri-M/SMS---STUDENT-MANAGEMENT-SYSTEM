package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.EnrollDto;

import java.util.List;

public interface EnrollService {
    EnrollDto createEnrollDto(EnrollDto enrollDto);

    EnrollDto getEnrollmentById(Long enrollId);

    List<EnrollDto> getAllEnrollments();

    EnrollDto updateEnrollment(Long enrollId, EnrollDto updateenrollDto);

    void deleteEnrollmentById(Long enrollId);

    void deleteAllEnrollments();
}
