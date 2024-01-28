package com.training.users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(length=40)
    private String name;

    @Email(message = "Formatação de email invalida.")
    @Column(unique=true, length=60)
    private String email;

    @Column(length=30)
    @Size(min=6, message = "A senha precisa ter ao menos 6 caracteres.")
    private String password;
}

