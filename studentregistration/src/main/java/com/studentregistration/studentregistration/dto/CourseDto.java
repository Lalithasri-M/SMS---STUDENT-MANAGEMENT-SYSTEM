package com.studentregistration.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long courseId;
    private String courseCode;
    private String courseName;
    private String duration;
    private Double fees;
}
