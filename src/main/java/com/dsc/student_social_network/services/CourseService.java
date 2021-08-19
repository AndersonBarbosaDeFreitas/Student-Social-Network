package com.dsc.student_social_network.services;

import com.dsc.student_social_network.entity.Comment;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.repository.CommentaryRepository;
import com.dsc.student_social_network.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public Course removeCourseById(int id) {
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);
        return course;
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).get();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesByGradeDesc() {
        return courseRepository.findAll(Sort.by(Sort.Direction.DESC, "grade"));
    }

    public Course setNameCourseById(int id, String newName) {
        Course course = courseRepository.findById(id).get();
        course.setName(newName);
        courseRepository.save(course);
        return course;
    }

    public Course setGradeCourseById(int id, String grade) {
        Course course = courseRepository.findById(id).get();
        course.setGrade(Double.parseDouble(grade));
        courseRepository.save(course);
        return course;
    }

    public Course addCommentToCourseById(int courseId, Comment comment) {
        Course course = courseRepository.findById(courseId).get();
        course.getCommentaries().add(comment);
        courseRepository.save(course);
        return course;
    }

    public Course removeCommentToCourseById(int courseId, String commentId) {
        Course course = courseRepository.findById(courseId).get();
        for (Comment comment:
             course.getCommentaries()) {
            if(comment.getId() == Integer.parseInt(commentId)) comment.setIsRemoved(1);
        }
        courseRepository.save(course);
        List<Comment> comments = commentaryRepository.findCommentariesByCourseIdAndIsRemovedEquals(Integer.parseInt(commentId), 0).get();
        course.setCommentaries(comments);
        return course;
    }

}
