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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "User Controller", description = "This is User Servcie Apis")
@Slf4j
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
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // creating fallback method

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        log.info("Fallback method because some servcie is down : {] }", ex.getMessage());
        User user = User.builder().name("Dummy")
                .email("dummy@gmail.com")
                .about("this is dummy user ")
                .userId("12234").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    // @PostMapping("/ratings")
    // ResponseEntity<Rating> createRating(@RequestBody Rating rating) {

    // return ResponseEntity.ok(userService.createRating(rating));
    // }

}
