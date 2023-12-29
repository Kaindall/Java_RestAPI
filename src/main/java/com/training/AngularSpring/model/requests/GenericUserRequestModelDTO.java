package com.training.AngularSpring.model.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GenericUserRequestModelDTO {
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;
    @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private String password;

}
