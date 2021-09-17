package com.dsc.student_social_network.services;

import com.dsc.student_social_network.dto.CommentDto;
import com.dsc.student_social_network.dto.CourseDto;
import com.dsc.student_social_network.dto.GradeDto;
import com.dsc.student_social_network.entity.Comment;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.entity.Grade;
import com.dsc.student_social_network.exception.CourseNotFoundException;
import com.dsc.student_social_network.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.removeCourseById");
        }
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);

        return new CourseDto(course);
    }

    public CourseDto getCourseById(int id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.getCourseById");
        }
        return new CourseDto(courseRepository.findById(id).get());
    }

    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtos = new ArrayList<>();
        courseRepository.findAll().forEach(course -> courseDtos.add(new CourseDto(course)));
        return courseDtos;
    }

    public List<CourseDto> getAllCoursesByGradeDesc() {
        List<CourseDto> courseDtos = new ArrayList<>();
        courseRepository.findAll().forEach(course -> courseDtos.add(new CourseDto(course)));
        courseDtos.sort(Comparator.comparing(a -> a.getGrade() * -1));
        return courseDtos;
    }

    public CourseDto setNameCourseById(int id, CourseDto courseDto) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.setNameCourseById");
        }
        Course course = courseRepository.findById(id).get();
        course.setName(courseDto.getName());
        courseRepository.save(course);
        return new CourseDto(course);
    }

    public CourseDto setGradeCourseById(int id, GradeDto gradeDto) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.setGradeCourseById");
        }
        Course course = courseRepository.findById(id).get();
        Grade newGrade = new Grade(course, gradeDto.getGrade());
        course.getGradeList().add(newGrade);
        return new CourseDto(courseRepository.save(course));
    }

    public CourseDto addCommentToCourseById(int id, CommentDto commentDto) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.addCommentToCourseById");
        }
        Course course = courseRepository.findById(id).get();
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCourse(course);
        comment.setUserEmail(commentDto.getUserEmail());
        course.getCommentaries().add(comment);
        courseRepository.save(course);
        return new CourseDto(course);
    }

    public CourseDto removeCommentToCourseById(int id, String commentId) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Disciplina não encontrada", "CourseService.removeCommentToCourseById");
        }
        Course course = courseRepository.findById(id).get();
        for (Comment comment : course.getCommentaries()) {
            if (comment.getId() == Integer.parseInt(commentId))
                comment.setIsRemoved(1);
        }
        courseRepository.save(course);
        return new CourseDto(course);
    }

}
