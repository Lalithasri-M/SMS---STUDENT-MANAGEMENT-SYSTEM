package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.EnrollDto;
import com.studentregistration.studentregistration.model.Enrollment;

public class EnrollMapper {

    public static EnrollDto mapToEnrollDto(Enrollment enrollment) {

        Long studentId = enrollment.getStudent() != null ? enrollment.getStudent().getStudentId() : null;
        Long courseId = enrollment.getCourse() != null ? enrollment.getCourse().getCourseId() : null;

        return new EnrollDto(
                enrollment.getEnrollId(),
                enrollment.getEntrollDate(),
                studentId,
                courseId,
                enrollment.getStudent(),
                enrollment.getCourse()
        );
    }

    public static Enrollment mapToEnrollment(EnrollDto enrollDto) {
        return new Enrollment(
                enrollDto.getEnrollId(),
                enrollDto.getEntrollDate(),
                null,
                null
        );
    }
}
