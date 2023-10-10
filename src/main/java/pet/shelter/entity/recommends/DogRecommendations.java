package pet.shelter.entity.recommends;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "dog_recomms")
public class DogRecommendations extends Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;
    private String firstTimeDogHandlerAdvices;

    public DogRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet,
                              String firstTimeDogHandlerAdvices) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
        this.firstTimeDogHandlerAdvices = firstTimeDogHandlerAdvices;
    }
}
