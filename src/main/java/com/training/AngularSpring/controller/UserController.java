package com.training.AngularSpring.controller;

import com.training.AngularSpring.model.request.UserRequestModel;
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
    Map<String, UserRequestModel> users;

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) {
        UserResponseModel responseValue = new UserResponseModel(userId, "Gabriel",
                "gabrielsilva@gmail.com");
        return new ResponseEntity<>(responseValue, HttpStatus.OK);
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
    public ResponseEntity<String> createUser(@NonNull @Valid @RequestBody UserRequestModel userInfo) {
        if (users == null) users = new HashMap<>();
        if (userInfo.getUserId() == null) {
            userInfo.setUserId(UUID.randomUUID().toString());
            users.put(userInfo.getUserId(), userInfo);
            String responseValue = "Conta com o identificador " + userInfo.getUserId() + " criada com sucesso. " +
                    "Bem-vindo, " + userInfo.getName() + "!";
            return new ResponseEntity<>(responseValue, HttpStatus.CREATED);
        } else if (users.containsKey(userInfo.getUserId())) {
            return new ResponseEntity<>("Conta já cadastrada", HttpStatus.CONFLICT);
        }

    }

    @PatchMapping
    public String editUser() {
        return "Usuário editado!";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Usuário deletado!";
    }
}
