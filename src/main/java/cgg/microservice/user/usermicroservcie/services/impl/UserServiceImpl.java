package cgg.microservice.user.usermicroservcie.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import cgg.microservice.user.usermicroservcie.entities.Hotel;
import cgg.microservice.user.usermicroservcie.entities.Rating;
import cgg.microservice.user.usermicroservcie.entities.User;
import cgg.microservice.user.usermicroservcie.exceptions.ResourceNotFoundException;
import cgg.microservice.user.usermicroservcie.repositories.UserRepository;
import cgg.microservice.user.usermicroservcie.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;
    // private HotelService hotelServcie;
    // private RatingService ratingService;
    private RestClient restClient;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMap -> {
            User user = userRepository.findById(userMap.getUserId()).orElseThrow(
                    () -> new ResourceNotFoundException("User with given id not found on server !!: " + userMap
                            .getUserId()));
            // api call to Rating Servcie to get the rating data
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userMap
                    .getUserId(),
                    Rating[].class);

            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
            log.info("{} ", ratingsOfUser);

            List<Rating> ratingsList = ratings.stream().map(rating -> {
                // api call to hotel servcie to get the hotel

                // ResponseEntity<Hotel> forEntity = restTemplate
                // .getForEntity("http://HOTELMICROSERVICE/hotels/" + rating.getHotelId(),
                // Hotel.class);
                // Hotel hotel = forEntity.getBody();
                // log.info("Response status code {}: ", forEntity.getStatusCode());
                // Hotel hotel = hotelServcie.getHotel(rating.getHotelId());
                Hotel hotel = restClient.get().uri("http://localhost:8082/hotels/" + rating.getHotelId())
                        .retrieve().body(Hotel.class);

                // set hotel in rating
                rating.setHotel(hotel);

                return rating;

            }).collect(Collectors.toList());

            user.setRatings(ratingsList);
            return user;
        }).collect(Collectors.toList());
    }

    // get single user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id not found on server !!: " + userId));
        // api call to Rating Servcie to get the rating data
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userId,
                Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        log.info("{} ", ratingsOfUser);

        List<Rating> ratingsList = ratings.stream().map(rating -> {
            // api call to hotel servcie to get the hotel

            // ResponseEntity<Hotel> forEntity = restTemplate
            // .getForEntity("http://HOTELMICROSERVICE/hotels/" + rating.getHotelId(),
            // Hotel.class);
            // Hotel hotel = forEntity.getBody();
            // log.info("Response status code {}: ", forEntity.getStatusCode());
            // Hotel hotel = hotelServcie.getHotel(rating.getHotelId());
            // set hotel in rating
            Hotel hotel = restClient.get().uri("http://HOTELMICROSERVICE/hotels/" + rating.getHotelId())
                    .retrieve().body(Hotel.class);
            rating.setHotel(hotel);

            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        return user;

    }

    // @Override
    // public Rating createRating(Rating rating) {

    // return ratingService.create(rating).getBody();

    // }

}
