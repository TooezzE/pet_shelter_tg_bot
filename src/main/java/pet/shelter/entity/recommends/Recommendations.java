package pet.shelter.entity.recommends;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public abstract class Recommendations {

    private String transport;
    private String homeImprovementForYoungPet;
    private String homeImprovementForAdultPet;
    private String recommendationsForDisabledPet;

    public Recommendations(String transport, String homeImprovementForYoungPet, String homeImprovementForAdultPet, String recommendationsForDisabledPet) {
        this.transport = transport;
        this.homeImprovementForYoungPet = homeImprovementForYoungPet;
        this.homeImprovementForAdultPet = homeImprovementForAdultPet;
        this.recommendationsForDisabledPet = recommendationsForDisabledPet;
    }
}

