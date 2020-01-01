package it.ronas.usermanagement.service;

import it.ronas.usermanagement.model.User;

import java.util.List;

/**
 * @author: <a href="mailto:nasi.ghaffari@gmail.com">Nastaran Ghaffari</a>
 * @date: 12/17/2019
 * @version: 1.0.0
 */

public interface UserService {

    User createUser(User user);

    User findUserById(int id);

    User updateUser(User user);

    Boolean deleteUser(int id);

    List<User> findAllUsers();
}
