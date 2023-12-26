package com.training.AngularSpring.controller;

import com.training.AngularSpring.model.User;
import com.training.AngularSpring.model.request.CreateUserRequestModel;
import com.training.AngularSpring.model.request.UserRequestModel;
import com.training.AngularSpring.model.response.UserResponseModel;
import com.training.AngularSpring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {this.userService = userService;}

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable int userId) {
        UserResponseModel foundUser = userService.getUser(userId);

        if (foundUser == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<?>> getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "limit", defaultValue = "20") int limit,
                                              @RequestParam(value = "order", defaultValue = "asc", required = false) String order) {
        List<UserResponseModel> responseValue = userService.getAllUsers();
        if (responseValue.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(responseValue, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@NonNull @Valid @RequestBody CreateUserRequestModel user) {
        UserResponseModel responseValue = userService.createUser(user);
        return new ResponseEntity<>(responseValue, HttpStatus.CREATED);
    }

    @PutMapping(path="/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editUser(@PathVariable int userId,
                           @RequestBody UserRequestModel user) {
        if (!users.containsKey(userId)) return new ResponseEntity<>("Usuário inexistente", HttpStatus.BAD_REQUEST);
        User existentUser = users.get(userId);
        existentUser.setName(user.getName());


        UserResponseModel responseUser = new UserResponseModel(existentUser.getUserId(), existentUser.getName(),
                existentUser.getEmail());
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        if (!users.containsKey(userId)) return new ResponseEntity<>("Usuário inexistente", HttpStatus.BAD_REQUEST);
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
