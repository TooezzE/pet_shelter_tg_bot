package pet.shelter.pets;

import pet.shelter.recommends.DogRecommendations;

import javax.persistence.*;

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

    public Dog(Long id, String name, int age, String breed, DogRecommendations dogRecommendations) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.dogRecommendations = dogRecommendations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public DogRecommendations getDogRecommendations() {
        return dogRecommendations;
    }

    public void setDogRecommendations(DogRecommendations dogRecommendations) {
        this.dogRecommendations = dogRecommendations;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", dogRecommendations=" + dogRecommendations +
                '}';
    }
}
