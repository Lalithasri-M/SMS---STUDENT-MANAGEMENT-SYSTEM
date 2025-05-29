package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.DeptDto;
import com.studentregistration.studentregistration.service.DeptService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/saveDepartment")
    public ResponseEntity<DeptDto> createDepartment(@RequestBody DeptDto deptDto) {
        DeptDto saveDepartment = deptService.createDeptDto(deptDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/getDepartmentById/{deptId}")
    public ResponseEntity<DeptDto> getDepartmentById(@PathVariable("deptId") Long deptId) {
        DeptDto deptDto = deptService.getDepartmentById(deptId);
        return new ResponseEntity<>(deptDto,HttpStatus.OK);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DeptDto>> getAllDepartments() {
        List<DeptDto> departments = deptService.getAllDepartments();
        return new ResponseEntity<>(departments,HttpStatus.OK);
    }

    @PutMapping("/updateDepartment/{deptId}")
    public ResponseEntity<DeptDto> updateDepartment(@PathVariable("deptId") Long deptId, @RequestBody DeptDto updateDeptDto) {
        DeptDto deptDto = deptService.updateDepartment(deptId,updateDeptDto);
        return new ResponseEntity<>(deptDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartmentById/{deptId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("deptId") Long deptId) {
        deptService.deleteDepartmentById(deptId);
        return ResponseEntity.ok("Department Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllDepartments")
    public ResponseEntity<String> deleteAllDepartments() {
        deptService.deleteAllDepartments();
        return ResponseEntity.ok("Departments Deleted Successfully!...");
    }
}
