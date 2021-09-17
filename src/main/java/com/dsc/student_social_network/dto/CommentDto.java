package com.dsc.student_social_network.dto;

import com.dsc.student_social_network.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private int id;
    private String content;
    private String userEmail;
    private LocalDateTime creationDate;

    public CommentDto() {
        super();
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userEmail = comment.getUserEmail();
        this.creationDate = comment.getCreationDate();
    }

    public CommentDto(String content, String userEmail) {
        this.content = content;
        this.userEmail = userEmail;
        this.creationDate = LocalDateTime.now();
    }
}
