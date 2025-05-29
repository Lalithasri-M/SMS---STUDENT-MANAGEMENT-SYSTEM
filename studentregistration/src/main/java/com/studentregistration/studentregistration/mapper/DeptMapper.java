package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.DeptDto;
import com.studentregistration.studentregistration.model.Department;

public class DeptMapper {

    public static DeptDto mapToDeptDto(Department department) {
        return new DeptDto(
                department.getDeptId(),
                department.getDeptCode(),
                department.getDeptName(),
                department.getHodName()
        );
    }

    public static Department mapToDepartment(DeptDto deptDto) {
        return new Department(
                deptDto.getDeptId(),
                deptDto.getDeptCode(),
                deptDto.getDeptName(),
                deptDto.getHodName()
        );
    }
}
