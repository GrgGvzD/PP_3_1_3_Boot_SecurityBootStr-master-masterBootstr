package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Set<String> roles;
}
