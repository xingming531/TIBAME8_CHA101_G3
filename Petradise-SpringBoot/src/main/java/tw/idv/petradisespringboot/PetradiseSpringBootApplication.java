package tw.idv.petradisespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetradiseSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetradiseSpringBootApplication.class, args);
	}

}
