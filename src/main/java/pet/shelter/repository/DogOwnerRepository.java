package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.shelter.model.DogOwners;

import java.util.Set;

public interface DogOwnerRepository extends JpaRepository<DogOwners,Long> {

    Set<DogOwners> findDogOwnerByChatId(Long chatId);
}
