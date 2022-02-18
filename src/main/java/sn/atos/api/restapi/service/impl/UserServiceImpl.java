package sn.atos.api.restapi.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.atos.api.restapi.exception.ResourceNotFoundException;
import sn.atos.api.restapi.model.User;
import sn.atos.api.restapi.repositories.UserRepository;
import sn.atos.api.restapi.service.UserService;

import java.lang.module.ResolutionException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on ::"+userId));

        return user;
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new ResolutionException("User not found");
        }
        User user = userOptional.get();

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setUpdatedAt(new Date());
        return user;
    }

    @Override
    public User createUser(User user) {
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public Map<String ,Boolean> deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Map<String , Boolean> response = new HashMap<>();
        if(userOptional.isEmpty()){
            response.put("delete", Boolean.FALSE);
        } else{
            response.put("delete", Boolean.TRUE);
            User user = userOptional.get();
            userRepository.delete(user);
        }

        return response ;
    }
}
