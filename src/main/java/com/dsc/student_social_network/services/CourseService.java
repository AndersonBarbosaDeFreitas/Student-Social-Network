package com.dsc.student_social_network.services;

import com.dsc.student_social_network.dto.CourseDto;
import com.dsc.student_social_network.entity.Comment;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.entity.Grade;
import com.dsc.student_social_network.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseDto addCourse(Course course) {
        Course courseRegistered = courseRepository.saveAndFlush(course);
        courseRegistered.getGradeList().add(new Grade(courseRegistered, courseRegistered.getLastGradeAdd()));
        return new CourseDto(courseRepository.saveAndFlush(courseRegistered));
    }

    public CourseDto removeCourseById(int id) {
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);

        return new CourseDto(course);
    }

    public CourseDto getCourseById(int id) {
        return new CourseDto(courseRepository.findById(id).get());
    }

    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtos = new ArrayList<>();
        courseRepository.findAll().forEach(course -> courseDtos.add(new CourseDto(course)));
        return courseDtos;
    }

    public List<CourseDto> getAllCoursesByGradeDesc() {
        List<CourseDto> courseDtos = new ArrayList<>();
        courseRepository.findAll(Sort.by(Sort.Direction.DESC, "grade"))
                .forEach(course -> courseDtos.add(new CourseDto(course)));
        return courseDtos;
    }

    public CourseDto setNameCourseById(int id, String newName) {
        Course course = courseRepository.findById(id).get();
        course.setName(newName);
        courseRepository.save(course);
        return new CourseDto(course);
    }

    public CourseDto setGradeCourseById(int id, Double grade) {
        Course course = courseRepository.findById(id).get();
        Grade newGrade = new Grade(course, grade);
        course.getGradeList().add(newGrade);
        return new CourseDto(courseRepository.save(course));
    }

    public CourseDto addCommentToCourseById(int courseId, Comment comment) {
        Course course = courseRepository.findById(courseId).get();
        course.getCommentaries().add(comment);
        courseRepository.save(course);
        return new CourseDto(course);
    }

    public CourseDto removeCommentToCourseById(int courseId, String commentId) {
        Course course = courseRepository.findById(courseId).get();
        for (Comment comment : course.getCommentaries()) {
            if (comment.getId() == Integer.parseInt(commentId))
                comment.setIsRemoved(1);
        }
        courseRepository.save(course);
        return new CourseDto(course);
    }

}
