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
    @Column(name = "cat_recomms")
    @OneToOne(targetEntity = CatRecommendations.class)
    private CatRecommendations catRecommendations;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private CatShelter shelter;

    public Cat(String name, int age, String breed, CatRecommendations catRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.catRecommendations = catRecommendations;
    }
}
