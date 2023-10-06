package pet.shelter.pets;

import pet.shelter.recommends.CatRecommendations;

import javax.persistence.*;

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

    public Cat(Long id, String name, int age, String breed, CatRecommendations catRecommendations) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.catRecommendations = catRecommendations;
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

    public CatRecommendations getCatRecommendations() {
        return catRecommendations;
    }

    public void setCatRecommendations(CatRecommendations catRecommendations) {
        this.catRecommendations = catRecommendations;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", catRecommendations=" + catRecommendations +
                '}';
    }
}
