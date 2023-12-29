package com.training.AngularSpring.controller;

import com.training.AngularSpring.exceptions.UserNotFoundException;
import com.training.AngularSpring.model.requests.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.requests.UserRequestModelDTO;
import com.training.AngularSpring.model.responses.UserResponseModelDTO;
import com.training.AngularSpring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {this.userService = userService;}

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModelDTO> getUser(@PathVariable int userId) {
        UserResponseModelDTO foundUser = userService.getUser(userId);

        if (foundUser == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<?>> getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "limit", defaultValue = "20") int limit,
                                              @RequestParam(value = "order", defaultValue = "asc", required = false) String order) {
        List<UserResponseModelDTO> responseValue = userService.getAllUsers();
        if (responseValue.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(responseValue, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@NonNull @Valid @RequestBody CreateUserRequestModelDTO user) {
        UserResponseModelDTO responseValue = userService.createUser(user);
        return new ResponseEntity<>(responseValue, HttpStatus.CREATED);
    }

    @PutMapping(path="/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> replaceUser(@PathVariable int userId,
                           @RequestBody UserRequestModelDTO user) {
        user.setUserId(userId);
        userService.replace(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        boolean isDeleted = userService.deleteUser(userId);

        if (!isDeleted) throw new UserNotFoundException();

        return ResponseEntity.noContent().build();
    }
}
