package cgg.microservice.user.usermicroservcie.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cgg.microservice.user.usermicroservcie.entities.Hotel;

@FeignClient(name = "HOTELMICROSERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
