package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.shelter.model.Cat;


public interface CatRepository extends JpaRepository<Cat, Long> {

    Cat findCatById(Long id);
}
