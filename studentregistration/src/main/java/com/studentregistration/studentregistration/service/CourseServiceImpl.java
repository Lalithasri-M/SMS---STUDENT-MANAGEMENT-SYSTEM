package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.CourseDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.CourseMapper;
import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = CourseMapper.mapToCourse(courseDto);

        if(courseRepository.existsByCourseCodeAndCourseName(courseDto.getCourseCode(),
                courseDto.getCourseName())) {
            throw new RuntimeException("Course name already exists");
        }

        Course addCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(addCourse);
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course does not exist with the provided Id : "+ courseId));
        return CourseMapper.mapToCourseDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> CourseMapper.mapToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto updatecourseDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course does not exist with the provided Id : "+ courseId));

        course.setCourseCode(updatecourseDto.getCourseCode());
        course.setCourseName(updatecourseDto.getCourseName());
        course.setDuration(updatecourseDto.getDuration());
        course.setFees(updatecourseDto.getFees());
        Course updateCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(updateCourse);
    }

    @Override
    public void deleteCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course does not exist with the provided Id : "+ courseId));

        courseRepository.deleteById(courseId);
    }

    @Override
    public void deleteAllCourses() {

        courseRepository.deleteAll();
    }
}
