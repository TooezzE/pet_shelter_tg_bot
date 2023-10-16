package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.animals.Cat;

@Repository
public interface CatsRepository extends JpaRepository<Cat, Long> {
}
