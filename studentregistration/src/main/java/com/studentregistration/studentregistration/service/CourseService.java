package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);

    CourseDto getCourseById(Long courseId);

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(Long courseId, CourseDto updatecourseDto);

    void deleteCourseById(Long courseId);

    void deleteAllCourses();
}
