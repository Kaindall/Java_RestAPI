package com.training.AngularSpring.controller;

import com.training.AngularSpring.model.request.UserRequestModel;
import com.training.AngularSpring.model.request.CreateUserRequestModel;
import com.training.AngularSpring.model.response.UserResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    Map<String, UserRequestModel> users = new HashMap<>();

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            UserRequestModel requestedUser = users.get(userId);
            UserResponseModel responseValue = new UserResponseModel(
                    userId, requestedUser.getName(), requestedUser.getEmail());
            return new ResponseEntity<>(responseValue, HttpStatus.OK);
        } else return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "20") int limit,
                          @RequestParam(value = "order", defaultValue = "asc", required = false) String order) {

        return String.format("Uma lista de usuário foi chamado na página %d" +
                "\nMostrando %d pagínas em ordem %s", page, limit, order);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createUser(@NonNull @Valid @RequestBody CreateUserRequestModel user) {
        if (users == null) users = new HashMap<>();
        if (user.getUserId() != null) {
            return new ResponseEntity<>("O campo ID deve ser nulo", HttpStatus.BAD_REQUEST);
        }
        if (users.values().stream()
                .anyMatch(currentUser -> user.getEmail().equals(currentUser.getEmail()))) {
            return new ResponseEntity<>("E-mail já cadastrado", HttpStatus.CONFLICT);
        }


        user.setUserId(UUID.randomUUID().toString());
        users.put(user.getUserId(), user);
        String responseValue = "Conta com o identificador " + user.getUserId() + " criada com sucesso. " +
                "Bem-vindo, " + user.getName() + "!";
        return new ResponseEntity<>(responseValue, HttpStatus.CREATED);
    }

    @PutMapping(path="/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity editUser(@PathVariable String userId,
                           @RequestBody UserRequestModel user) {
        if (!users.containsKey(userId)) return new ResponseEntity<>("Usuário inexistente", HttpStatus.BAD_REQUEST);
        UserRequestModel existentUser = users.get(userId);
        existentUser.setName(user.getName());
        users.put(userId, existentUser);

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
