package com.training.AngularSpring.service;

import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.UserRequestModelDTO;
import com.training.AngularSpring.model.responses.UserResponseModelDTO;

import java.util.List;

public interface UserService {
    UserResponseModelDTO getUser(int userId);

    List<UserResponseModelDTO> getAllUsers();

    UserResponseModelDTO createUser(CreateUserRequestModelDTO user);

    UserResponseModelDTO editUser (int userId, UserRequestModelDTO user);

    boolean deleteUser (int user);
}
