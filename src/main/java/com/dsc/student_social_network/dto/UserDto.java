package com.dsc.student_social_network.dto;

import com.dsc.student_social_network.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
