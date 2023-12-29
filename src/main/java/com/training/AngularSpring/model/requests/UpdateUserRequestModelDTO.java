package com.training.AngularSpring.model.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
public class UpdateUserRequestModelDTO {
    @NonNull private Optional<Integer> userId;
    private Optional<String> name;
    @Email(message = "Formatação de email invalida.")
    private Optional<String> email;
    @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private Optional<String> password;
}
