package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.DogAdopter;

import java.util.Set;

@Repository
public interface DogOwnerRepository extends JpaRepository<DogAdopter,Long> {

    Set<DogAdopter> findDogOwnerByChatId(Long chatId);
}
