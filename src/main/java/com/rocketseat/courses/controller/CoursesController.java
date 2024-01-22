package com.rocketseat.courses.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courses.exception.ResourceNotFoundException;
import com.rocketseat.courses.model.Course;
import com.rocketseat.courses.repository.CoursesRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class CoursesController {
    
    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping("/course")
    public List<Course> getAllCourses(){
        return coursesRepository.findAll();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getRoomById(@PathVariable(value = "id") long courseId)
    throws ResourceNotFoundException {
        Course course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado:: " + courseId));
        return ResponseEntity.ok().body(course);
    }

    @PostMapping("/course")
    public Course createCourse(@Valid @RequestBody Course course){
        return coursesRepository.save(course);
    }

    
    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateRoom(@PathVariable(value = "id") Long courseId, @Valid @RequestBody Course courseDetails) throws ResourceNotFoundException{
        Course course = coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com esse id::" + courseId));
        course.setName(courseDetails.getName());
        course.setCategory(courseDetails.getCategory());
        course.setActive(courseDetails.getActive());
        final Course updateCourse = coursesRepository.save(course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/course/{id}")
     public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException{
        Course course = coursesRepository.findById(courseId)
        .orElseThrow(()-> new ResourceNotFoundException("Curso não encontrada com esse id::" + courseId));

        coursesRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
     }



}