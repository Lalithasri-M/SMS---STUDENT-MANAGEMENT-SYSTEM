package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.EnrollDto;
import com.studentregistration.studentregistration.service.EnrollService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/enrollment")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PostMapping("/saveEnrollment")
    public ResponseEntity<EnrollDto> createEnrollment(@RequestBody EnrollDto enrollDto) {
        EnrollDto saveEnrollment = enrollService.createEnrollDto(enrollDto);
        return new ResponseEntity<>(saveEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/getEnrollmentById/{enrollId}")
    public ResponseEntity<EnrollDto> getEnrollmentById(@PathVariable("enrollId") Long enrollId) {
        EnrollDto enrollDto = enrollService.getEnrollmentById(enrollId);
        return new ResponseEntity<>(enrollDto,HttpStatus.OK);
    }

    @GetMapping("/getAllEnrollments")
    public ResponseEntity<List<EnrollDto>> getAllEnrollments() {
        List<EnrollDto> enrollments = enrollService.getAllEnrollments();
        return new ResponseEntity<>(enrollments,HttpStatus.OK);
    }

    @PutMapping("/updateEnrollment/{enrollId}")
    public ResponseEntity<EnrollDto> updateEnrollment(@PathVariable("enrollId") Long enrollId, @RequestBody EnrollDto updateEnrollDto) {
        EnrollDto enrollDto = enrollService.updateEnrollment(enrollId,updateEnrollDto);
        return new ResponseEntity<>(enrollDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteEnrollmentById/{enrollId}")
    public ResponseEntity<String> deleteEnrollmentById(@PathVariable("enrollId") Long enrollId) {
        enrollService.deleteEnrollmentById(enrollId);
        return ResponseEntity.ok("Enrollment Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllEnrollments")
    public ResponseEntity<String> deleteAllEnrollments() {
        enrollService.deleteAllEnrollments();
        return ResponseEntity.ok("Enrollments Deleted Successfully!...");
    }
}
