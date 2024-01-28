package com.judapp.users.service;

import com.judapp.users.model.requests.CreateUserRequestModelDTO;
import com.judapp.users.model.requests.UpdateUserRequestModelDTO;
import com.judapp.users.model.responses.UserResponseModelDTO;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserService {
    UserResponseModelDTO getUser(int userId);

    LinkedHashMap<String, Object> getAllUsers(int page, int size);

    UserResponseModelDTO createUser(CreateUserRequestModelDTO user);

    List<UserResponseModelDTO> createAllUsers(List<CreateUserRequestModelDTO> users);

    UserResponseModelDTO editUser (UpdateUserRequestModelDTO user);

    boolean replace(int userId, CreateUserRequestModelDTO user);

    boolean deleteUser (int user);
}
