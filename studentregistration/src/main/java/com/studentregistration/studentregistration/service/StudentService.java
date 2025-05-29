package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentId, StudentDto updatestudentDto);

    void deleteStudentById(Long studentId);

    void deleteAllStudents();
}
