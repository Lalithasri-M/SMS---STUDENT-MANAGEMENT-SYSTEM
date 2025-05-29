package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.DeptDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.DeptMapper;
import com.studentregistration.studentregistration.model.Department;
import com.studentregistration.studentregistration.repository.DeptRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public DeptDto createDeptDto(DeptDto deptDto) {
        Department department = DeptMapper.mapToDepartment(deptDto);
        Department addDepartment = deptRepository.save(department);
        return DeptMapper.mapToDeptDto(addDepartment);
    }

    @Override
    public DeptDto getDepartmentById(Long deptId) {
        Department department = deptRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with the provided Id : "+ deptId));
        return DeptMapper.mapToDeptDto(department);
    }

    @Override
    public List<DeptDto> getAllDepartments() {
        List<Department> departments = deptRepository.findAll();
        return departments.stream().map((department) -> DeptMapper.mapToDeptDto(department)).collect(Collectors.toList());
    }

    @Override
    public DeptDto updateDepartment(Long deptId, DeptDto updatedeptDto) {
        Department department = deptRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with the provided Id : "+ deptId));

        department.setDeptCode(updatedeptDto.getDeptCode());
        department.setDeptName(updatedeptDto.getDeptName());
        department.setHodName(updatedeptDto.getHodName());
        Department updateDepartment = deptRepository.save(department);
        return DeptMapper.mapToDeptDto(updateDepartment);
    }

    @Override
    public void deleteDepartmentById(Long deptId) {
        Department department = deptRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with the provided Id : "+ deptId));

        deptRepository.deleteById(deptId);
    }

    @Override
    public void deleteAllDepartments() {
        deptRepository.deleteAll();
    }
}
