package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.shelter.model.CatOwners;

import java.util.Set;

public interface CatOwnerRepository extends JpaRepository<CatOwners,Long> {

    Set<CatOwners> findCatOwnerByChatId(Long chatId);
}
