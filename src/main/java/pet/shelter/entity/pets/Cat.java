package pet.shelter.entity.pets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.recommends.CatRecommendations;
import pet.shelter.entity.shelters.CatShelter;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String breed;
    private boolean isDisabled;
    @OneToOne(mappedBy = "cat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_recomms_id")
    private CatRecommendations catRecommendations;

    public Cat(String name, int age, String breed, boolean isDisabled, CatRecommendations catRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.isDisabled = isDisabled;
        if(!isDisabled) {
            catRecommendations.setRecommendationsForDisabledPet(null);
        }
        if(age > 5) {
            catRecommendations.setHomeImprovementForYoungPet(null);
        } else {
            catRecommendations.setHomeImprovementForAdultPet(null);
        }
        this.catRecommendations = catRecommendations;
    }

    public Cat() {
    }
}
