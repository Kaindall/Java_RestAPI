package com.training.AngularSpring.model.request;

import com.training.AngularSpring.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class CreateUserRequestModel {
    @NonNull private String name;
    @NonNull @Email(message = "Formatação de email invalida.")
    private String email;
    @NonNull @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private String password;
}
