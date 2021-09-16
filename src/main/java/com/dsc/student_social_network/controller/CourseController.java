package com.dsc.student_social_network.controller;

import com.dsc.student_social_network.dto.CourseDto;
import com.dsc.student_social_network.entity.Comment;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/grade")
    public ResponseEntity<List<CourseDto>> getRankingByGrade() {
        return new ResponseEntity<>(courseService.getAllCoursesByGradeDesc(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CourseDto> addCourse(@RequestBody Course newCourse) {
        return new ResponseEntity<>(courseService.addCourse(newCourse), HttpStatus.CREATED);
    }

    @PutMapping("/name/{id}")
    public ResponseEntity<CourseDto> setNameCourseById(@PathVariable Integer id,
            @RequestParam(value = "name", defaultValue = "") String name) {
        try {
            return new ResponseEntity<>(courseService.setNameCourseById(id, name), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<CourseDto> setGradeCourseById(@PathVariable Integer id,
            @RequestParam(value = "grade", defaultValue = "0") Double grade) {
        try {
            return new ResponseEntity<>(courseService.setGradeCourseById(id, grade), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<CourseDto> addCommentToCourseById(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(courseService.addCommentToCourseById(id, comment), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<CourseDto> removeCommentToCourseById(@PathVariable Integer id,
            @RequestParam(value = "commentId", defaultValue = "0") String commentId) {
        try {
            return new ResponseEntity<>(courseService.removeCommentToCourseById(id, commentId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> removeCourseById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(courseService.removeCourseById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new CourseDto(new Course(null, 0)), HttpStatus.NOT_FOUND);
        }
    }
}
