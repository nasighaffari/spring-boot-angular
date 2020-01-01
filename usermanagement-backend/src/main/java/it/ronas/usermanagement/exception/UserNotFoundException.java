package it.ronas.usermanagement.exception;

/**
 * @author: <a href="mailto:nasi.ghaffari@gmail.com">Nastaran Ghaffari</a>
 * @date: 12/30/2019
 * @version: 1.0.0
 */
public class UserNotFoundException extends Exception {

    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }
}
