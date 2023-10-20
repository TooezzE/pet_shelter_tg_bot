package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.CatAdopter;

import java.util.Set;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatAdopter,Long> {

    Set<CatAdopter> findCatOwnerByChatId(Long chatId);
}
