package pet.shelter.entity.recommends;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatRecommendations extends Recommendations {

    public CatRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
    }
}
