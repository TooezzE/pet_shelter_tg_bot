package pet.shelter.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс  собак
 */
@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private int birthday;
    @Column(name = "description")
    private String descriptionOfThePet;

    public Dog(String name, String breed, int birthday, String descriptionOfThePet) {
        this.name = name;
        this.breed = breed;
        this.birthday = birthday;
        this.descriptionOfThePet = descriptionOfThePet;
    }

    public Dog() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public int getBirthday() {
        return birthday;
    }

    public String getDescriptionOfThePet() {
        return descriptionOfThePet;
    }

    public void setDescriptionOfThePet(String descriptionOfThePet) {
        this.descriptionOfThePet = descriptionOfThePet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return birthday == dog.birthday && Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(breed, dog.breed) && Objects.equals(descriptionOfThePet, dog.descriptionOfThePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, birthday, descriptionOfThePet);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animalBreed='" + breed + '\'' +
                ", yearOfBirth=" + birthday +
                ", descriptionOfThePet='" + descriptionOfThePet + '\'' +
                '}';
    }
}
