package com.dsc.student_social_network.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String content;
    private String userEmail;
    private LocalDateTime creationDate = LocalDateTime.now();
    private int isRemoved = 0;

    public Comment(String content, Course course, String userEmail) {
        super();
        this.content = content;
        this.course = course;
        this.userEmail = userEmail;
    }

    public Comment() {
        super();
    }

}
