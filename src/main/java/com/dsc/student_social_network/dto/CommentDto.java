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

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userEmail = comment.getUserEmail();
        this.creationDate = comment.getCreationDate();
    }
}
