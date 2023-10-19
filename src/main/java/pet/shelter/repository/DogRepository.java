package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.shelter.model.Cat;
import pet.shelter.model.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findDogById(Long id);
}
