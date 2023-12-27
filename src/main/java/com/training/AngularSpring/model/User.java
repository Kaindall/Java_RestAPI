package com.training.AngularSpring.model;

import com.training.AngularSpring.model.request.CreateUserRequestModelDTO;
import com.training.AngularSpring.model.request.UserRequestModelDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(length=40)
    private String name;

    @Email(message = "Formatação de email invalida.")
    @Column(unique=true, length=60)
    private String email;

    @Column(length=30)
    @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private String password;

    public User(CreateUserRequestModelDTO userRequestModel) {
        this.name = userRequestModel.getName();
        this.email = userRequestModel.getEmail();
        this.password = userRequestModel.getPassword();
    }

    public User(UserRequestModelDTO userRequestModel) {
        this.name = userRequestModel.getName();
        this.email = userRequestModel.getEmail();
        this.password = userRequestModel.getPassword();
    }
}

