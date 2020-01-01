package it.ronas.usermanagement.service;

import it.ronas.usermanagement.data.UserRepository;
import it.ronas.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: <a href="mailto:nasi.ghaffari@gmail.com">Nastaran Ghaffari</a>
 * @date: 12/17/2019
 * @version: 1.0.0
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);//.isPresent() ? userRepository.findById(id).get() : new User();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(int id) {
//        Optional<User> byId = userRepository.findById(id);
//        byId.ifPresent(user -> userRepository.delete(user));
        userRepository.delete(userRepository.findById(id));
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
