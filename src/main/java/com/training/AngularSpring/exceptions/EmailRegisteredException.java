package com.training.AngularSpring.exceptions;

public class EmailRegisteredException extends RuntimeException{
    public EmailRegisteredException () {
        super("O e-mail já está cadastrado.");
    }
}
