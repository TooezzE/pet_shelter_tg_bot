package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.shelters.DogShelter;

@Repository
public interface DogShelterRepository extends JpaRepository<DogShelter, Long> {
}
