package pet.shelter.entity.recommends;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Recommendations {

    @Column(name = "transport")
    private String transport;
    @Column(name = "home_improve_for_young")
    private String homeImprovementForYoungPet;
    @Column(name = "home_improve_for_adult")
    private String homeImprovementForAdultPet;
    @Column(name = "recomms_for_disabled")
    private String recommendationsForDisabledPet;

    public Recommendations(String transport,
                           String homeImprovementForYoungPet,
                           String homeImprovementForAdultPet,
                           String recommendationsForDisabledPet) {
        this.transport = transport;
        this.homeImprovementForYoungPet = homeImprovementForYoungPet;
        this.homeImprovementForAdultPet = homeImprovementForAdultPet;
        this.recommendationsForDisabledPet = recommendationsForDisabledPet;
    }

    public Recommendations() {
    }
}

