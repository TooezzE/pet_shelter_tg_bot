package pet.shelter.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Dog class
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
    private boolean isAdopted;

    public Dog(Long id, String name, String breed, int birthday, String descriptionOfThePet) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.birthday = birthday;
        this.descriptionOfThePet = descriptionOfThePet;
        this.isAdopted = false;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted(boolean adopted) {
        isAdopted = adopted;
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
