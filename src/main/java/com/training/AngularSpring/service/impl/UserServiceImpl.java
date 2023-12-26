package com.training.AngularSpring.service.impl;

import com.training.AngularSpring.exceptions.EmailRegisteredException;
import com.training.AngularSpring.model.User;
import com.training.AngularSpring.model.request.CreateUserRequestModel;
import com.training.AngularSpring.model.request.UserRequestModel;
import com.training.AngularSpring.model.response.UserResponseModel;
import com.training.AngularSpring.repository.UserRepository;
import com.training.AngularSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    UserServiceImpl (UserRepository userRepository) {this.userRepository = userRepository;}

    public UserResponseModel getUser(int userId) {
        User currentUser = userRepository.findById((userId));

        if (currentUser != null) return new UserResponseModel(currentUser);

        return null;
    }

    public List<UserResponseModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseModel::new)
                .toList();
    }

    public UserResponseModel createUser(CreateUserRequestModel user){
        List<User> users = userRepository.findAll();

        if (users.stream()
                .anyMatch(currentUser -> user.getEmail().equals(currentUser.getEmail()))) {
            throw new EmailRegisteredException();
        }

        User createdUser = userRepository.save(user);

        return new UserResponseModel(createdUser);
    }

    public UserResponseModel editUser(UserRequestModel user) {
        return null;
    }

    public boolean deleteUser(UserRequestModel user) {
        return false;
    }
}
