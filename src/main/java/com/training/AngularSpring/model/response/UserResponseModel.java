package com.training.AngularSpring.model.response;

import com.training.AngularSpring.model.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseModel {
    @NonNull private int userId;
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;

    public UserResponseModel(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

