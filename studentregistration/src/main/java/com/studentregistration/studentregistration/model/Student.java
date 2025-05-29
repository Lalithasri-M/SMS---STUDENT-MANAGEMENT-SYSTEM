package com.studentregistration.studentregistration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "studentRegistration", uniqueConstraints = @UniqueConstraint(columnNames =
        {"firstName","lastName","phoneNo"}))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String firstName;
    private String lastName;
    private String gender;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    private String email;
    private String phoneNo;
    private String address;

    @Column(name = "regDate")
    private LocalDate registrationDate;
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;
}
