package com.biblioApp.core.controllersImpl;

import com.biblioApp.core.controllers.UserController;
import com.biblioApp.core.entities.User;
import com.biblioApp.core.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    // http://localhost:8888/user (GET)
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    @Override
    public List<User> getUser() {
        return null;
    }

    // http://localhost:8888/user/1 (GET)
    @Override
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // http://localhost:8888/user/add (ADD)
    @Override
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json")
    public User addUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public String inhabilitateUser(Long id) {
        //TO DO
        return null;
    }

    // http://localhost:8888/user/update (PATCH)
    @Override
    @RequestMapping(value = "/user/update", method = RequestMethod.PATCH, produces = "application/json")
    public String updateUser(User userUpdate) {
        return userService.updateUser(userUpdate);
    }
    // http://localhost:8888/test (GET)
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    @Override
    public String test() {
        return "Test done";
    }
}
