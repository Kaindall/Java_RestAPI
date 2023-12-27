package com.training.AngularSpring.mapper;

import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.UserRequestModelDTO;
import com.training.AngularSpring.model.responses.UserResponseModelDTO;
import org.mapstruct.Mapper;

import com.training.AngularSpring.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(UserRequestModelDTO userRequestModelDTO);

    public abstract User toEntity(CreateUserRequestModelDTO createUserRequestModelDTO);

    public abstract User toEntity(UserResponseModelDTO userResponseModelDTO);

    public abstract UserResponseModelDTO toUserResponseModel(User user);

    public abstract UserResponseModelDTO toUserResponseModel(UserRequestModelDTO userRequestModelDTO);
}
