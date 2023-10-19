package pet.shelter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Класс  котов
 */
@Entity
public class Cat {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String animalBreed;
    private int yearOfBirth;
    private String descriptionOfThePet;

    public Cat(Long id, String name, String animalBreed, int yearOfBirth, String descriptionOfThePet) {
        this.id = id;
        this.name = name;
        this.animalBreed = animalBreed;
        this.yearOfBirth = yearOfBirth;
        this.descriptionOfThePet = descriptionOfThePet;
    }

    public Cat() {
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
        Cat cat = (Cat) o;
        return yearOfBirth == cat.yearOfBirth && Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(animalBreed, cat.animalBreed) && Objects.equals(descriptionOfThePet, cat.descriptionOfThePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animalBreed, yearOfBirth, descriptionOfThePet);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animalBreed='" + animalBreed + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", descriptionOfThePet='" + descriptionOfThePet + '\'' +
                '}';
    }
}
