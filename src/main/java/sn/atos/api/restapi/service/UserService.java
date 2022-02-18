package sn.atos.api.restapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sn.atos.api.restapi.exception.ResourceNotFoundException;
import sn.atos.api.restapi.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();

    public User getUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException;

    public User updateUser(@PathVariable(value="id") Long userId, @RequestBody User userDetails);

    User createUser(@RequestBody User user);

    Map<String ,Boolean> deleteUser(@PathVariable(value="id") Long userId);
}
