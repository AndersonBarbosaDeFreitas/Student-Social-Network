package com.dsc.student_social_network.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Courses")
public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = -62953813495872660L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double grade;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentaries = new ArrayList<>();

    public Course() { super(); }

    public Course(String name, double grade) {
        super();
        this.name = name;
        this.grade = grade;
    }
}
