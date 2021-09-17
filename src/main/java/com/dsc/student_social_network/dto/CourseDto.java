package com.dsc.student_social_network.dto;

import com.dsc.student_social_network.entity.Comment;
import com.dsc.student_social_network.entity.Course;
import com.dsc.student_social_network.entity.Grade;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Data
public class CourseDto {
    private int id;
    private String name;
    private double grade;
    private List<CommentDto> commentaries = new ArrayList<>();

    public CourseDto() {
        super();
    }

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.grade = getAverageGrade(course.getGradeList());
        convertCommentaries(course.getCommentaries());
    }

    public CourseDto(String name) {
        this.name = name;
    }

    private void convertCommentaries(List<Comment> commentaries) {
        commentaries.forEach(comment -> {
            if (comment.getIsRemoved() == 0) {
                var commentConverted = new CommentDto(comment);
                this.commentaries.add(commentConverted);
            }
        });
    }

    private double getAverageGrade(List<Grade> grades) {
        OptionalDouble average = grades.stream().mapToDouble(gradeObj -> gradeObj.getGrade()).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
