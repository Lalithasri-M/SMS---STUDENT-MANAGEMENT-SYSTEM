package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.StudentDto;
import com.studentregistration.studentregistration.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto saveStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentById/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDto updateStudentDto) {
      StudentDto studentDto = studentService.updateStudent(studentId,updateStudentDto);
      return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudentById/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllStudents")
    public ResponseEntity<String> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok("Students Deleted Successfully!...");
    }
}
