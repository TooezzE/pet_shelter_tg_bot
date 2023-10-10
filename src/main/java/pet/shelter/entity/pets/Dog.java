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
    @Column(name = "dog_recomms")
    @OneToOne(targetEntity = DogRecommendations.class)
    private DogRecommendations dogRecommendations;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private DogShelter shelter;

    public Dog(String name, int age, String breed, DogRecommendations dogRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.dogRecommendations = dogRecommendations;
    }
}
