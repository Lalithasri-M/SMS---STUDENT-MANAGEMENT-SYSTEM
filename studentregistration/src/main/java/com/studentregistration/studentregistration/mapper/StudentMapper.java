package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.StudentDto;
import com.studentregistration.studentregistration.model.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        Long courseId = student.getCourse() != null ? student.getCourse().getCourseId() : null;
        Long deptId = student.getDepartment() != null ? student.getDepartment().getDeptId() : null;

        return new StudentDto(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getDateOfBirth(),
                student.getEmail(),
                student.getPhoneNo(),
                student.getAddress(),
                student.getRegistrationDate(),
                student.getPaymentStatus(),
                courseId,
                deptId,
                student.getCourse(),
                student.getDepartment()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getStudentId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getGender(),
                studentDto.getDateOfBirth(),
                studentDto.getEmail(),
                studentDto.getPhoneNo(),
                studentDto.getAddress(),
                studentDto.getRegistrationDate(),
                studentDto.getPaymentStatus(),
                null,
                null
        );
    }
}
