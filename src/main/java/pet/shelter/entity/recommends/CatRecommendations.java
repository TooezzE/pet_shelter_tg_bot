package pet.shelter.entity.recommends;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cat_recomms")
public class CatRecommendations extends Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;

    public CatRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
    }
}
