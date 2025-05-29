package com.studentregistration.studentregistration.dto;

import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNo;
    private String address;
    private LocalDate registrationDate;
    private String paymentStatus;
    private Long courseId;
    private Long deptId;
    private Course course;
    private Department department;
}
