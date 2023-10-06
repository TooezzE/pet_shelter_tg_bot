package pet.shelter.pets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.recommends.CatRecommendations;

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
    private CatRecommendations catRecommendations;

    public Cat(String name, int age, String breed, CatRecommendations catRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.catRecommendations = catRecommendations;
    }
}
