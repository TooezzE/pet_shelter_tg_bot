package pet.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pet.shelter.pets.Dog;
import pet.shelter.recommends.DogRecommendations;

@SpringBootApplication
public class ShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterApplication.class, args);
	}

}
