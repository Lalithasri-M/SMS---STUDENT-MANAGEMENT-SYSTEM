package com.studentregistration.studentregistration.dto;

import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDto {

    private Long enrollId;
    private LocalDate entrollDate;
    private Long studentId;
    private Long courseId;
    private Student student;
    private Course course;
}
