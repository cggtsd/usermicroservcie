package cgg.microservice.user.usermicroservcie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsermicroservcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermicroservcieApplication.class, args);
	}

}
