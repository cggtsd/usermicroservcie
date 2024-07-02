package cgg.microservice.user.usermicroservcie.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    // extra properties you want to manage

    public ResourceNotFoundException() {
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
