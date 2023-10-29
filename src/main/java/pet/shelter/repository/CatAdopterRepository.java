package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.CatAdopter;

import java.util.Set;

@Repository
public interface CatAdopterRepository extends JpaRepository<CatAdopter,Long> {

    CatAdopter findCatAdopterByChatId(Long chatId);
}
