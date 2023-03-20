package com.example.icetime.iceTimeApp.service;

import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.dto.UserDto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
