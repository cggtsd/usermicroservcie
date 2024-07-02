package cgg.microservice.user.usermicroservcie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.microservice.user.usermicroservcie.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
