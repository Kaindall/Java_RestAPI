package com.training.AngularSpring.model.response;

import com.training.AngularSpring.model.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseModelDTO {
    @NonNull private int userId;
    private String name;
    @Email(message = "Formatação de email invalida.")
    private String email;

    public UserResponseModelDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    @Autowired
    public User generateEntity (User user) {

    }
}

