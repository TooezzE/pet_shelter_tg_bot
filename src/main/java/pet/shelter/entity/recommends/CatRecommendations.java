package pet.shelter.entity.recommends;

import lombok.Getter;
import lombok.Setter;
import pet.shelter.entity.animals.Cat;

import javax.persistence.*;

/**
 * Cat recommendations class
 */
@Getter
@Setter
@Entity
@Table(name = "cat_recomms")
public class CatRecommendations extends Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;
    public CatRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
    }

    public CatRecommendations() {
    }
}
