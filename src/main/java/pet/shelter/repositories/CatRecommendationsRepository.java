package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.recommends.CatRecommendations;

@Repository
public interface CatRecommendationsRepository extends JpaRepository<CatRecommendations, Long> {
}