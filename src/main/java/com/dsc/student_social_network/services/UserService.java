package com.dsc.student_social_network.services;

import com.dsc.student_social_network.dto.UserDto;
import com.dsc.student_social_network.entity.User;
import com.dsc.student_social_network.exception.UserExistsException;
import com.dsc.student_social_network.exception.UserInvalidException;
import com.dsc.student_social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto getUser(int id) {
        return new UserDto(userRepository.findById(id).get());
    }

    public UserDto addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExistsException();
        userRepository.save(user);
        return new UserDto(user);
    }

    public UserDto removeUser(int userId) {
        User user = validateUser(userId);

        userRepository.delete(user);
        return new UserDto(user);
    }

    public UserDto updateUser(int userId, User userData) {
        User user = validateUser(userId);
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        userRepository.save(user);
        return new UserDto(user);
    }

    private User validateUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserInvalidException();

        return user.get();
    }

    public boolean validateUser(User user) {
        Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
        return (userOpt.isPresent() && userOpt.get().getPassword().equals(user.getPassword()));
    }
}
