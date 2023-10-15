package pet.shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.entity.recommends.DogRecommendations;

@Repository
public interface DogRecommendationsRepository extends JpaRepository<DogRecommendations, Long> {
}
