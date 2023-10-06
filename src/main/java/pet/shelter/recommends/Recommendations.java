package pet.shelter.recommends;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class Recommendations {

    private String transport;
    private String homeImprovementForYoungPet;
    private String homeImprovementForAdultPet;
    private String recommendationsForDisabledPet;
}
