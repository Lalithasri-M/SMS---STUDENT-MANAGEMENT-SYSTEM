package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.DeptDto;

import java.util.List;

public interface DeptService {

    DeptDto createDeptDto(DeptDto deptDto);

    DeptDto getDepartmentById(Long deptId);

    List<DeptDto> getAllDepartments();

    DeptDto updateDepartment(Long deptId, DeptDto updatedeptDto);

    void deleteDepartmentById(Long deptId);

    void deleteAllDepartments();
}
