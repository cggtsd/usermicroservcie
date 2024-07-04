package cgg.microservice.user.usermicroservcie.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cgg.microservice.user.usermicroservcie.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> create(@RequestBody Rating rating);

    @GetMapping("/ratings")
    ResponseEntity<List<Rating>> getRatings();

    @GetMapping("/ratings/users/{userId}")
    ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/ratings/hotels/{hotelId}")
    ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId);

}
