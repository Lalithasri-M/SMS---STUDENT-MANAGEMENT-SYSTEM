package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enrollment,Long> {
}
