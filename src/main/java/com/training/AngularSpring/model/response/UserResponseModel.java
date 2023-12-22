package com.training.AngularSpring.model.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseModel {
    @NonNull private String userId;
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;
}

