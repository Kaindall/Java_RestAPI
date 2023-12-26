package com.training.AngularSpring.service;

import com.training.AngularSpring.model.User;
import com.training.AngularSpring.model.request.CreateUserRequestModel;
import com.training.AngularSpring.model.request.UserRequestModel;
import com.training.AngularSpring.model.response.UserResponseModel;

import java.util.List;

public interface UserService {

    UserResponseModel getUser(int userId);

    List<UserResponseModel> getAllUsers();

    UserResponseModel createUser(CreateUserRequestModel user);

    UserResponseModel editUser (UserRequestModel user);

    boolean deleteUser (UserRequestModel user);
}
