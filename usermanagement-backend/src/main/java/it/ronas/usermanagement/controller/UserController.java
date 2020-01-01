package it.ronas.usermanagement.controller;

import it.ronas.usermanagement.exception.UserNotFoundException;
import it.ronas.usermanagement.model.User;
import it.ronas.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author: <a href="mailto:nasi.ghaffari@gmail.com">Nastaran Ghaffari</a>
 * @date: 12/17/2019
 * @version: 1.0.0
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) throws UserNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userService.findUserById(id));
        userOptional.orElseThrow(() -> new UserNotFoundException("No user found"));
        return userOptional.get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws UserNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userService.findUserById(user.getId()));
        userOptional.orElseThrow(() -> new UserNotFoundException("No user found"));
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userService.findUserById(id));
        userOptional.orElseThrow(() -> new UserNotFoundException("No user found"));
        userOptional.ifPresent(user -> userService.deleteUser(user.getId()));
    }

}
