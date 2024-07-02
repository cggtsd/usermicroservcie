package cgg.microservice.user.usermicroservcie.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cgg.microservice.user.usermicroservcie.entities.User;
import cgg.microservice.user.usermicroservcie.exceptions.ResourceNotFoundException;
import cgg.microservice.user.usermicroservcie.repositories.UserRepository;
import cgg.microservice.user.usermicroservcie.services.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id not found on server !!: " + userId));
    }

}
