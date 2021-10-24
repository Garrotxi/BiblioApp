package com.biblioApp.core.controllers;

import com.biblioApp.core.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserController {
    public List<User> getUser();

    public Optional<User> getUserById(Long id);

    public User addUser(User user);

    public String inhabilitateUser(Long id);

    public String updateUser(User userUpdate);

    public String test();
}
