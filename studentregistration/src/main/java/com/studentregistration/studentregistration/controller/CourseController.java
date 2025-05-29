package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.CourseDto;
import com.studentregistration.studentregistration.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/saveCourse")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto saveCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(saveCourse, HttpStatus.CREATED);
    }

    @GetMapping("/getCourseById/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") Long courseId) {
        CourseDto courseDto = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseDto,HttpStatus.OK);
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseDto updateCourseDto) {
        CourseDto courseDto = courseService.updateCourse(courseId,updateCourseDto);
        return new ResponseEntity<>(courseDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourseById/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok("Course Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllCourses")
    public ResponseEntity<String> deleteAllCourses() {
        courseService.deleteAllCourses();
        return ResponseEntity.ok("Courses Deleted Successfully!...");
    }
}
