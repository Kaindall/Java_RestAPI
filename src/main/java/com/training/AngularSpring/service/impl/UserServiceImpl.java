package com.training.AngularSpring.service.impl;

import com.training.AngularSpring.exceptions.EmailRegisteredException;
import com.training.AngularSpring.model.User;
import com.training.AngularSpring.model.request.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.request.UserRequestModelDTO;
import com.training.AngularSpring.model.response.UserResponseModelDTO;
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

    public UserResponseModelDTO getUser(int userId) {
        User currentUser = userRepository.findByUserId((userId));

        if (currentUser != null) return new UserResponseModelDTO(currentUser);

        return null;
    }

    public List<UserResponseModelDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseModelDTO::new)
                .toList();
    }

    public UserResponseModelDTO createUser(CreateUserRequestModelDTO user){
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailRegisteredException();

        User createdUser = userRepository.save(new User(user));

        return new UserResponseModelDTO(createdUser);
    }

    public UserResponseModelDTO editUser(UserRequestModelDTO user) {
        if (!userRepository.existsByUserId(user.getUserId())) return null;

        User editedUser = userRepository.save(new User(user));

        return new UserResponseModelDTO((editedUser));
    }

    public boolean deleteUser(int userId) {
        if (!userRepository.existsByUserId(userId)) return false;

        userRepository.deleteById(userId);
        return true;
    }
}
