package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.shelter.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
