package cgg.microservice.user.usermicroservcie.services;

import java.util.List;

import cgg.microservice.user.usermicroservcie.entities.Rating;
import cgg.microservice.user.usermicroservcie.entities.User;

public interface UserService {
    // User operations

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUsers();

    // get single user of given userId

    User getUser(String userId);

    // TODO: delete
    // TODO: update

    Rating createRating(Rating rating);

}
