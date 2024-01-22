package com.rocketseat.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rocketseat.courses.model.Course;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long>{
    
}
