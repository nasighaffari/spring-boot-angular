package it.ronas.usermanagement;

import it.ronas.usermanagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserManagementApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    private String getBaseUrl() {
        return "/api/users";
    }

    @Test
    void testCrudOperation() {
        User user = new User();

        user.setFirstName("Nastaran");
        user.setLastName("Ghaffari");
        ResponseEntity<User> createdUser = restTemplate.postForEntity(getBaseUrl(), user, User.class);
        System.out.println("createdUser1 = " + createdUser.getBody());
        Assertions.assertNotNull(createdUser.getBody());

        user.setFirstName("Yasaman");
        user.setLastName("Ghaffari");
        createdUser = restTemplate.postForEntity(getBaseUrl(), user, User.class);
        System.out.println("createdUser2 = " + createdUser.getBody());
        Assertions.assertNotNull(createdUser.getBody());
        //
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, entity, String.class);
        System.out.println("users = " + response.getBody());
        Assertions.assertNotNull(response.getBody());
        //
        int id = 1;
        user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        System.out.println("beforeUpdateUser User = " + user);
        Assertions.assertNotNull(user);

        user.setFirstName("Nastaran");
        user.setLastName("Ghaderi");
        restTemplate.put(getBaseUrl(), user);

        user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        System.out.println("afterUpdateUser User = " + user);
        Assertions.assertNotNull(user);
        //
        id = 2;
        user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        System.out.println("beforeDeleteUser User = " + user);
        Assertions.assertNotNull(user);

        restTemplate.delete(getBaseUrl() + "/" + id);
        //
        response = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, entity, String.class);
        System.out.println("users after update and delete = " + response.getBody());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("Nastaran");
        user.setLastName("Ghaffari");
        ResponseEntity<User> createdUser = restTemplate.postForEntity(getBaseUrl(), user, User.class);
        Assertions.assertNotNull(createdUser.getBody());
    }

    @Test
    void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void testGetUserById() {
        User user = restTemplate.getForObject(getBaseUrl() + "/1", User.class);
        Assertions.assertNotNull(user);
    }

    @Test
    void testUpdateUser() {
        int id = 1;
        User user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        Assertions.assertNotNull(user);

        user.setFirstName("Nastaran");
        user.setLastName("Ghaderi");
        restTemplate.put(getBaseUrl(), user);
    }

    @Test
    void testDeleteUser() {
        int id = 2;
        User user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        Assertions.assertNotNull(user);

        restTemplate.delete(getBaseUrl() + "/" + id);
    }

}
