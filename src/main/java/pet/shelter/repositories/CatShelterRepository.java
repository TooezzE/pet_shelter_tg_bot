package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.shelters.CatShelter;

@Repository
public interface CatShelterRepository extends JpaRepository<Long, CatShelter> {
}
