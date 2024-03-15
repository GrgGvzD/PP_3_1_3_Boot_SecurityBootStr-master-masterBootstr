package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
//    void addUser(User user);
    User createUser(User user);

//    void editUserById(User user);

    User updateUserById(User user);

    void removeUserById(Long id);

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByUserName(String login);

}
