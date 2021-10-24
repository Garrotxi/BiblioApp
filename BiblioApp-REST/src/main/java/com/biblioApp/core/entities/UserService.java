package com.biblioApp.core.entities;

import java.util.List;
import java.util.Optional;
import com.biblioApp.core.entities.User;

public interface UserService {

    public List<User> findUser();

    public Optional<User> findUserById(Long id);

    public User saveUser(User userNew);

    public String inhabilitateUser(Long id);

    public String updateUser(User userUpdated);


}
