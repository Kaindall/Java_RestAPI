package com.training.AngularSpring.model;

import com.training.AngularSpring.model.request.CreateUserRequestModel;

public class UserMapper {
    public static User toNewEntity(User user, CreateUserRequestModel userToConvert) {
        user.setName((userToConvert.getName()));
        user.setEmail(userToConvert.getEmail());
        user.setPassword(userToConvert.getPassword());

        return user;
    }
}
