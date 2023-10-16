package pet.shelter.entity.animals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.recommends.DogRecommendations;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Entity
@Table(name = "dogs")
public class Dog extends Animal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "dog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_recomms_id")
    private DogRecommendations dogRecommendations;

    public Dog(String name,
               int age,
               String breed,
               boolean isDisabled,
               boolean isAdopted,
               DogRecommendations dogRecommendations) {
        super(name, age, breed, isDisabled, isAdopted);
        if(!isDisabled) {
            dogRecommendations.setRecommendationsForDisabledPet(null);
        }
        this.dogRecommendations = dogRecommendations;
        if (age > 5) {
            dogRecommendations.setHomeImprovementForYoungPet(null);
        } else {
            dogRecommendations.setHomeImprovementForAdultPet(null);

        }
    }

    public Dog() {
    }
}
