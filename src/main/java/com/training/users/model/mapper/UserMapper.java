package com.training.users.model.mapper;

import com.training.users.model.User;
import com.training.users.model.requests.CreateUserRequestModelDTO;
import com.training.users.model.requests.GenericUserRequestModelDTO;
import com.training.users.model.responses.UserResponseModelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(GenericUserRequestModelDTO genericUserRequestModelDTO);

    public abstract User toEntity(CreateUserRequestModelDTO createUserRequestModelDTO);

    public abstract GenericUserRequestModelDTO toGenericUserModel(CreateUserRequestModelDTO createUserRequestModelDTO);

    public abstract UserResponseModelDTO toUserResponseModel(User user);

}
