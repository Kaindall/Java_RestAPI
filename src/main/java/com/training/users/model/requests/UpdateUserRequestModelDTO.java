package com.training.users.model.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UpdateUserRequestModelDTO {
    @NonNull private Integer userId;
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;
    @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private String password;

    public boolean isNamePresent() {return this.name != null;}

    public boolean isEmailPresent() {return this.email != null;}

    public boolean isPasswordPresent(){return this.password != null;}
}
