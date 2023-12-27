package com.training.AngularSpring.service.impl;

import com.training.AngularSpring.exceptions.EmailRegisteredException;
import com.training.AngularSpring.exceptions.UserNotFoundException;
import com.training.AngularSpring.mapper.UserMapper;
import com.training.AngularSpring.model.User;
import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.UserRequestModelDTO;
import com.training.AngularSpring.model.responses.UserResponseModelDTO;
import com.training.AngularSpring.repository.UserRepositoryDAO;
import com.training.AngularSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepositoryDAO userRepositoryDAO;
    UserMapper userMapper;

    @Autowired
    UserServiceImpl (UserRepositoryDAO userRepositoryDAO, UserMapper userMapper) {
        this.userRepositoryDAO = userRepositoryDAO;
        this.userMapper = userMapper;
    }

    public UserResponseModelDTO getUser(int userId) {
        User currentUser = userRepositoryDAO.findByUserId((userId));

        if (currentUser != null) return userMapper.toUserResponseModel(currentUser);

        return null;
    }

    public List<UserResponseModelDTO> getAllUsers() {
        return userRepositoryDAO.findAll()
                .stream()
                .map(userMapper::toUserResponseModel)
                .toList();
    }

    public UserResponseModelDTO createUser(CreateUserRequestModelDTO user){
        if (userRepositoryDAO.existsByEmail(user.getEmail())) throw new EmailRegisteredException();

        return userMapper.toUserResponseModel(userRepositoryDAO.save(userMapper.toEntity(user)));
    }

    public UserResponseModelDTO editUser(int userId, UserRequestModelDTO user) {
        if (!userRepositoryDAO.existsByUserId(userId)) throw new UserNotFoundException();

        return userMapper.toUserResponseModel(userRepositoryDAO.save(userMapper.toEntity(user)));
    }

    public boolean deleteUser(int userId) {
        if (!userRepositoryDAO.existsByUserId(userId)) return false;

        userRepositoryDAO.deleteById(userId);
        return true;
    }
}
