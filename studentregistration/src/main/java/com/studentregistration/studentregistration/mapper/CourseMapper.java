package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.CourseDto;
import com.studentregistration.studentregistration.model.Course;

public class CourseMapper {

    public static CourseDto mapToCourseDto(Course course) {
        return new CourseDto(
                course.getCourseId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getDuration(),
                course.getFees()
        );
    }

    public static Course mapToCourse(CourseDto courseDto) {
        return new Course(
                courseDto.getCourseId(),
                courseDto.getCourseCode(),
                courseDto.getCourseName(),
                courseDto.getDuration(),
                courseDto.getFees()
        );
    }
}
