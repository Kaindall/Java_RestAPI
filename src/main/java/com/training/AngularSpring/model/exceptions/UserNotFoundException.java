package com.training.AngularSpring.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException () {super("Usuário inexistente.");}
}
