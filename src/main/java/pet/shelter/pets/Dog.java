package pet.shelter.pets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.recommends.DogRecommendations;

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
    private DogRecommendations dogRecommendations;

    public Dog(String name, int age, String breed, DogRecommendations dogRecommendations) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.dogRecommendations = dogRecommendations;
    }
}
