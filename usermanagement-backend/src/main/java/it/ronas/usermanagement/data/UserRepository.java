package it.ronas.usermanagement.data;

import it.ronas.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: <a href="mailto:nasi.ghaffari@gmail.com">Nastaran Ghaffari</a>
 * @date: 12/17/2019
 * @version: 1.0.0
 */

public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);
}
