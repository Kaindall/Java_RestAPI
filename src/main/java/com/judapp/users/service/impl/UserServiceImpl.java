package com.judapp.users.service.impl;

import com.judapp.users.model.exceptions.EmailRegisteredException;
import com.judapp.users.model.exceptions.UserNotFoundException;
import com.judapp.users.model.mapper.UserMapper;
import com.judapp.users.model.requests.CreateUserRequestModelDTO;
import com.judapp.users.model.requests.GenericUserRequestModelDTO;
import com.judapp.users.model.requests.UpdateUserRequestModelDTO;
import com.judapp.users.model.responses.UserResponseModelDTO;
import com.judapp.users.repository.UserRepositoryDAO;
import com.judapp.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    UserRepositoryDAO userRepositoryDAO;
    UserMapper userMapper;

    @Autowired
    UserServiceImpl (UserRepositoryDAO userRepositoryDAO, UserMapper userMapper) {
        this.userRepositoryDAO = userRepositoryDAO;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserResponseModelDTO getUser(int userId) {
        return userRepositoryDAO.findByUserId((userId))
                .map(userMapper::toUserResponseModel)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public LinkedHashMap<String, Object> getAllUsers(int page, int size) {
        page = (page > 0) ? page-1: 0;
        Page<UserResponseModelDTO> responsePage = userRepositoryDAO.findAll(PageRequest.of(page, size))
                .map(userMapper::toUserResponseModel);

        return new LinkedHashMap<>(Map.of(
                "content", responsePage.getContent(),
                "info", Map.of(
                        "currentPage", responsePage.getPageable().getPageNumber()+1,
                        "pageSize", responsePage.getPageable().getPageSize(),
                        "totalPages", responsePage.getTotalPages(),
                        "totalElements", responsePage.getTotalElements()
                )
            )
        );
    }

    @Transactional
    public UserResponseModelDTO createUser(CreateUserRequestModelDTO user){
        if (userRepositoryDAO.existsByEmail(user.getEmail())) throw new EmailRegisteredException("E-mail já registrado.");

        return userMapper.toUserResponseModel(userRepositoryDAO.save(userMapper.toEntity(user)));
    }

    @Transactional
    public List<UserResponseModelDTO> createAllUsers(List<CreateUserRequestModelDTO> users) {
        return users.stream().map(this::createUser).toList();
    }

    @Transactional
    public UserResponseModelDTO editUser(UpdateUserRequestModelDTO user) {
        return userMapper.toUserResponseModel(userRepositoryDAO.findByUserId(user.getUserId())
                .map(foundUser -> {
                    if (user.isNamePresent()) {foundUser.setName(user.getName());}
                    if (user.isEmailPresent()) {foundUser.setEmail(user.getEmail());}
                    if (user.isPasswordPresent()) {foundUser.setPassword(user.getPassword());}
                    userRepositoryDAO.save(foundUser);
                    return foundUser;
                })
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public boolean replace(int userId, CreateUserRequestModelDTO user) {
        GenericUserRequestModelDTO genericUser = userMapper.toGenericUserModel(user);
        genericUser.setUserId(userId);
        return userRepositoryDAO.findByUserId(genericUser.getUserId())
                .map(currentUser -> {
                    userRepositoryDAO.save(userMapper.toEntity(genericUser));
                    return true;
                })
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public boolean deleteUser(int userId) {
        if(!userRepositoryDAO.existsByUserId(userId)) throw new UserNotFoundException();

        userRepositoryDAO.deleteById(userId);
        return true;
    }
}
