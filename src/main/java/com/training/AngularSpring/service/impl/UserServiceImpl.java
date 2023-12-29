package com.training.AngularSpring.service.impl;

import com.training.AngularSpring.exceptions.EmailRegisteredException;
import com.training.AngularSpring.exceptions.UserNotFoundException;
import com.training.AngularSpring.mapper.UserMapper;
import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.GenericUserRequestModelDTO;
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
        return userRepositoryDAO.findByUserId((userId))
                .map(userMapper::toUserResponseModel)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<UserResponseModelDTO> getAllUsers() {
        return userRepositoryDAO.findAll()
                .stream()
                .map(userMapper::toUserResponseModel)
                .toList();
    }

    public UserResponseModelDTO createUser(CreateUserRequestModelDTO user){
        if (userRepositoryDAO.existsByEmail(user.getEmail())) throw new EmailRegisteredException("E-mail já registrado.");

        return userMapper.toUserResponseModel(userRepositoryDAO.save(userMapper.toEntity(user)));
    }

    //está criando novo usuário, editar
    public UserResponseModelDTO editUser(GenericUserRequestModelDTO user) {
        if (!userRepositoryDAO.existsByUserId(user.getUserId())) throw new UserNotFoundException();
        return null;
    }

    public boolean replace(GenericUserRequestModelDTO user) {
        return userRepositoryDAO.findByUserId(user.getUserId())
                .map(currentUser -> {
                    userRepositoryDAO.delete(currentUser);
                    userRepositoryDAO.save(userMapper.toEntity(user));
                    return true;
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean deleteUser(int userId) {
        if (!userRepositoryDAO.existsByUserId(userId)) throw new UserNotFoundException();

        userRepositoryDAO.deleteById(userId);
        return true;
    }
}
