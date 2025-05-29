package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByFirstNameAndLastNameAndPhoneNo(String firstName, String lastName, String phoneNo);
}
