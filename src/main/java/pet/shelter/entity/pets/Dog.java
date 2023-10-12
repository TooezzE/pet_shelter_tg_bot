package pet.shelter.entity.pets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.recommends.DogRecommendations;
import pet.shelter.entity.shelters.DogShelter;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String breed;
    private boolean isDisabled;
    @OneToOne(mappedBy = "dog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_recomms_id")
    private DogRecommendations dogRecommendations;

    public Dog(String name, int age, String breed, boolean isDisabled, DogRecommendations dogRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.isDisabled = isDisabled;
        if(!isDisabled) {
            dogRecommendations.setRecommendationsForDisabledPet(null);
        }
        if(age > 5) {
            dogRecommendations.setHomeImprovementForYoungPet(null);
        } else {
            dogRecommendations.setHomeImprovementForAdultPet(null);
        }
        this.dogRecommendations = dogRecommendations;
    }

    public Dog() {
    }
}
