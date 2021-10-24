package com.biblioApp.core.serviceImpl;

import com.biblioApp.core.entities.User;
import com.biblioApp.core.entities.UserService;
import com.biblioApp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findUser() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User saveUser(User userNew) {
        if (userNew != null) {
            return userRepository.save(userNew);
        }
        return new User();
    }

    @Override
    public String inhabilitateUser(Long id) {
        return null;
    }

    @Override
    public String updateUser(User userUpdated) {
        Long num = userUpdated.getId();
        if (userRepository.findById(num).isPresent()) {
            User userToUpdate = new User();
            userToUpdate.setEmail(userUpdated.getEmail());
            userToUpdate.setFirstName(userUpdated.getFirstName());
            userToUpdate.setLastName(userUpdated.getLastName());
            userToUpdate.setPassword(userUpdated.getPassword());
            userToUpdate.setPhoneNumber(userUpdated.getPhoneNumber());
            return "User updated";
        }
        return "Error while modifying user";
    }
}
