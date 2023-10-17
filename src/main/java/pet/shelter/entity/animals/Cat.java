package pet.shelter.entity.animals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.recommends.CatRecommendations;

import javax.persistence.*;

/**
 * Cat's class
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "cats")
public class Cat extends Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "cat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_recomms_id")
    private CatRecommendations catRecommendations;

    public Cat(String name,
               int age,
               String breed,
               boolean isDisabled,
               boolean isAdopted,
               CatRecommendations catRecommendations) {
        super(name, age, breed, isDisabled, isAdopted);
        if(!isDisabled) {
            catRecommendations.setRecommendationsForDisabledPet(null);
        }
        this.catRecommendations = catRecommendations;
        if(age > 5) {
                catRecommendations.setHomeImprovementForYoungPet(null);
            } else {
                catRecommendations.setHomeImprovementForAdultPet(null);
            }
    }

    public Cat() {
    }
}
