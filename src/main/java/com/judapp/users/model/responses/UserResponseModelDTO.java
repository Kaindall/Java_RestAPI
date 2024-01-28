package com.judapp.users.model.responses;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserResponseModelDTO {
    @NonNull private Integer userId;
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;
}

