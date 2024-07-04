package cgg.microservice.user.usermicroservcie.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.microservice.user.usermicroservcie.entities.Rating;
import cgg.microservice.user.usermicroservcie.entities.User;
import cgg.microservice.user.usermicroservcie.external.services.RatingService;
import cgg.microservice.user.usermicroservcie.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User saveUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    // get single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(@RequestBody Rating rating) {

        return ResponseEntity.ok(userService.createRating(rating));
    }

}
