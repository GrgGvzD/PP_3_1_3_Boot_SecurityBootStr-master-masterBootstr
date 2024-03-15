package ru.kata.spring.boot_security.demo.util;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super(String.format("User by id '%d' is not found", id));
    }

    public UserNotFoundException(String login) {
        super(String.format("User by login '%s' is not found", login));
    }
}
