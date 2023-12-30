package com.training.AngularSpring.service;

import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.GenericUserRequestModelDTO;
import com.training.AngularSpring.model.requests.UpdateUserRequestModelDTO;
import com.training.AngularSpring.model.responses.UserResponseModelDTO;

import java.util.List;

public interface UserService {
    UserResponseModelDTO getUser(int userId);

    List<UserResponseModelDTO> getAllUsers();

    UserResponseModelDTO createUser(CreateUserRequestModelDTO user);

    List<UserResponseModelDTO> createAllUsers(List<CreateUserRequestModelDTO> users);

    UserResponseModelDTO editUser (UpdateUserRequestModelDTO user);

    boolean replace(int userId, CreateUserRequestModelDTO user);

    boolean deleteUser (int user);
}
