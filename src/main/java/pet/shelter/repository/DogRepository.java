package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.Dog;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findDogByName(String name);
    List<Dog> findDogsByIsAdopted(boolean isAdopted);
}
