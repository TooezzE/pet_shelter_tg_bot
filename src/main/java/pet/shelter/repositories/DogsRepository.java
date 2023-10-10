package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.pets.Dog;

@Repository
public interface DogsRepository extends JpaRepository<Long, Dog> {
}
