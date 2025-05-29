package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    boolean existsByCourseCodeAndCourseName(String courseCode,String courseName);
}
