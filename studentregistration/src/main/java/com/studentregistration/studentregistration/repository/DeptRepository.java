package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Department,Long> {

    boolean existsByDeptCodeAndDeptName(String deptCode, String deptName);
}
