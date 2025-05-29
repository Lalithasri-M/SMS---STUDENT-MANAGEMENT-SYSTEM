package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.StudentDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.StudentMapper;
import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.Department;
import com.studentregistration.studentregistration.model.Student;
import com.studentregistration.studentregistration.repository.CourseRepository;
import com.studentregistration.studentregistration.repository.DeptRepository;
import com.studentregistration.studentregistration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);

        if(studentRepository.existsByFirstNameAndLastNameAndPhoneNo(studentDto.getFirstName(),
                studentDto.getLastName(),studentDto.getPhoneNo())) {
            throw new RuntimeException("Student name already exists with this phone number.");
        }

        Course course = courseRepository.findById(studentDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        student.setCourse(course);

        Department department = deptRepository.findById(studentDto.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        student.setDepartment(department);

        Student addStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(addStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with the provided Id : "+ studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatestudentDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with the provided Id : "+ studentId));

        student.setFirstName(updatestudentDto.getFirstName());
        student.setLastName(updatestudentDto.getLastName());
        student.setGender(updatestudentDto.getGender());
        student.setDateOfBirth(updatestudentDto.getDateOfBirth());
        student.setEmail(updatestudentDto.getEmail());
        student.setPhoneNo(updatestudentDto.getPhoneNo());
        student.setAddress(updatestudentDto.getAddress());
        student.setRegistrationDate(updatestudentDto.getRegistrationDate());
        student.setPaymentStatus(updatestudentDto.getPaymentStatus());

        Course course = courseRepository.findById(updatestudentDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + updatestudentDto.getCourseId()));
        student.setCourse(course);

        Department department = deptRepository.findById(updatestudentDto.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + updatestudentDto.getDeptId()));
        student.setDepartment(department);

        Student updateStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with the provided Id : "+ studentId));

        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
