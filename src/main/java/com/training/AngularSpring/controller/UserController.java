package com.training.AngularSpring.controller;

import com.training.AngularSpring.model.request.UserRequestModel;
import com.training.AngularSpring.model.response.UserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) {
        UserResponseModel responseValue = new UserResponseModel(userId, "Gabriel");
        return new ResponseEntity<UserResponseModel>(responseValue, HttpStatus.OK);
    }

    @GetMapping
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "20") int limit,
                          @RequestParam(value = "order", defaultValue = "asc", required = false) String order) {

        return String.format("Uma lista de usuário foi chamado na página %d" +
                "\nMostrando %d pagínas em ordem %s", page, limit, order);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userInfo) {
        UserResponseModel responseValue = new UserResponseModel(userInfo.getId(), userInfo.getName());
        return new ResponseEntity<>(responseValue, HttpStatus.CREATED);
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
