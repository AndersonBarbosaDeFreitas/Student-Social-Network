package com.dsc.student_social_network.controller;

import com.dsc.student_social_network.dto.CommentDto;
import com.dsc.student_social_network.dto.CourseDto;
import com.dsc.student_social_network.dto.GradeDto;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<CourseDto>> getRankingByGrade() {
        return new ResponseEntity<>(courseService.getAllCoursesByGradeDesc(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CourseDto> addCourse(@RequestBody Course newCourse) {
        return new ResponseEntity<>(courseService.addCourse(newCourse), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/nome")
    public ResponseEntity<CourseDto> setNameCourseById(@PathVariable Integer id, @RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.setNameCourseById(id, courseDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}/nota")
    public ResponseEntity<CourseDto> setGradeCourseById(@PathVariable Integer id, @RequestBody GradeDto gradeDto) {
        return new ResponseEntity<>(courseService.setGradeCourseById(id, gradeDto), HttpStatus.OK);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<CourseDto> addCommentToCourseById(@PathVariable Integer id,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(courseService.addCommentToCourseById(id, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<CourseDto> removeCommentToCourseById(@PathVariable Integer id,
            @RequestParam(value = "commentId", defaultValue = "0") String commentId) {
        return new ResponseEntity<>(courseService.removeCommentToCourseById(id, commentId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> removeCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.removeCourseById(id), HttpStatus.OK);
    }
}
