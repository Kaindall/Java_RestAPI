package com.training.AngularSpring.service;

import com.training.AngularSpring.model.request.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.request.UserRequestModelDTO;
import com.training.AngularSpring.model.response.UserResponseModelDTO;

import java.util.List;

public interface UserService {
    UserResponseModelDTO getUser(int userId);

    List<UserResponseModelDTO> getAllUsers();

    UserResponseModelDTO createUser(CreateUserRequestModelDTO user);

    UserResponseModelDTO editUser (UserRequestModelDTO user);

    boolean deleteUser (int user);
}
