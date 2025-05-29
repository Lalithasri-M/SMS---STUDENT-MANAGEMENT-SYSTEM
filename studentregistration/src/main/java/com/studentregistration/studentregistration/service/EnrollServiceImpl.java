package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.EnrollDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.EnrollMapper;
import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.Enrollment;
import com.studentregistration.studentregistration.model.Student;
import com.studentregistration.studentregistration.repository.CourseRepository;
import com.studentregistration.studentregistration.repository.EnrollRepository;
import com.studentregistration.studentregistration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollServiceImpl implements EnrollService{

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EnrollDto createEnrollDto(EnrollDto enrollDto) {
        Enrollment enrollment = EnrollMapper.mapToEnrollment(enrollDto);

        Student student = studentRepository.findById(enrollDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        enrollment.setStudent(student);

        Course course = courseRepository.findById(enrollDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        enrollment.setCourse(course);

        Enrollment addEntrollment = enrollRepository.save(enrollment);
        return EnrollMapper.mapToEnrollDto(addEntrollment);
    }

    @Override
    public EnrollDto getEnrollmentById(Long enrollId) {
        Enrollment enrollment = enrollRepository.findById(enrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Enroll Student does not exist with the provided Id : "+ enrollId));
        return EnrollMapper.mapToEnrollDto(enrollment);
    }

    @Override
    public List<EnrollDto> getAllEnrollments() {
        List<Enrollment> enrollments = enrollRepository.findAll();
        return enrollments.stream().map((enrollment) -> EnrollMapper.mapToEnrollDto(enrollment)).collect(Collectors.toList());
    }

    @Override
    public EnrollDto updateEnrollment(Long enrollId, EnrollDto updateenrollDto) {
        Enrollment enrollment = enrollRepository.findById(enrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Enroll Student does not exist with the provided Id : "+ enrollId));

        enrollment.setEntrollDate(updateenrollDto.getEntrollDate());

        Student student = studentRepository.findById(updateenrollDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + updateenrollDto.getStudentId()));
        enrollment.setStudent(student);

        Course course = courseRepository.findById(updateenrollDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + updateenrollDto.getCourseId()));
        enrollment.setCourse(course);

        Enrollment updateEnrollment = enrollRepository.save(enrollment);
        return EnrollMapper.mapToEnrollDto(updateEnrollment);
    }

    @Override
    public void deleteEnrollmentById(Long enrollId) {
        Enrollment enrollment = enrollRepository.findById(enrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Enroll Student does not exist with the provided Id : "+ enrollId));

        enrollRepository.deleteById(enrollId);
    }

    @Override
    public void deleteAllEnrollments() {
        enrollRepository.deleteAll();
    }
}
