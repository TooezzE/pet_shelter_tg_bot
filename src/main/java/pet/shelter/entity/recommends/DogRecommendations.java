package pet.shelter.entity.recommends;

import lombok.*;
import pet.shelter.entity.pets.Dog;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "dog_recomms")
public class DogRecommendations extends Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @Column(name = "handler_advices")
    private String firstTimeDogHandlerAdvices;

    public DogRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet,
                              String firstTimeDogHandlerAdvices) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
        this.firstTimeDogHandlerAdvices = firstTimeDogHandlerAdvices;
    }

    public DogRecommendations() {
    }
}
