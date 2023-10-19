package pet.shelter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Класс  собак
 */
@Entity
public class Dog {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String animalBreed;
    private int yearOfBirth;
    private String descriptionOfThePet;

    public Dog(Long id, String name, String animalBreed, int yearOfBirth, String descriptionOfThePet) {
        this.id = id;
        this.name = name;
        this.animalBreed = animalBreed;
        this.yearOfBirth = yearOfBirth;
        this.descriptionOfThePet = descriptionOfThePet;
    }

    public Dog() {
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

    public String getAnimalBreed() {
        return animalBreed;
    }

    public void setAnimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
        return yearOfBirth == dog.yearOfBirth && Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(animalBreed, dog.animalBreed) && Objects.equals(descriptionOfThePet, dog.descriptionOfThePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animalBreed, yearOfBirth, descriptionOfThePet);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animalBreed='" + animalBreed + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", descriptionOfThePet='" + descriptionOfThePet + '\'' +
                '}';
    }
}
