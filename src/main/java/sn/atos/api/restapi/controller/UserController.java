package sn.atos.api.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.atos.api.restapi.exception.ResourceNotFoundException;
import sn.atos.api.restapi.model.User;
import sn.atos.api.restapi.repositories.UserRepository;
import sn.atos.api.restapi.service.UserService;

import java.util.*;


/**
 * - GET /api/v1/users
 *
 * 	- POST /api/v1/users
 *
 * 	- GET /api/v1/users/{id}
 *
 * 	- PUT /api/v1/users/{id}
 *
 * 	- DELETE /api/v1/users/{id}
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException{
        User user = userService.getUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") Long userId, @RequestBody User userDetails){
        User user = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId){
        return userService.deleteUser(userId);
    }

}
