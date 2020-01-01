package it.ronas.usermanagement;

import it.ronas.usermanagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl() + "/users",
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void testGetUserById() {
        User user = restTemplate.getForObject(getBaseUrl() + "/users/1", User.class);
        System.out.println(user.getFirstName());
        Assertions.assertNotNull(user);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("Nastaran");
        user.setLastName("Ghaffari");
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getBaseUrl() + "/users", user, User.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    void testUpdateUser() {
        int id = 1;
        User user = restTemplate.getForObject(getBaseUrl() + "/" + id, User.class);
        user.setFirstName("Yasaman");
        user.setLastName("Ghaffari");
        restTemplate.put(getBaseUrl() + "/" + id, user);
        User updatedUser = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        Assertions.assertNotNull(updatedUser);
    }

    @Test
    void testDeleteUser() {
        int id = 2;
        User user = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        Assertions.assertNotNull(user);
        restTemplate.delete(getBaseUrl() + "/users/" + id);
        try {
            user = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        } catch (final HttpClientErrorException e) {
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
