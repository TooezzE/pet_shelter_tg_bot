package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
