package com.judapp.users.model.mapper;

import com.judapp.users.model.User;
import com.judapp.users.model.requests.CreateUserRequestModelDTO;
import com.judapp.users.model.requests.GenericUserRequestModelDTO;
import com.judapp.users.model.responses.UserResponseModelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(GenericUserRequestModelDTO genericUserRequestModelDTO);

    public abstract User toEntity(CreateUserRequestModelDTO createUserRequestModelDTO);

    public abstract GenericUserRequestModelDTO toGenericUserModel(CreateUserRequestModelDTO createUserRequestModelDTO);

    public abstract UserResponseModelDTO toUserResponseModel(User user);

}
