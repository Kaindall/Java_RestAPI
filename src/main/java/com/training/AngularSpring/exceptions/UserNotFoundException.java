package com.training.AngularSpring.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException () {super("Usuário inexistente.");}
}
