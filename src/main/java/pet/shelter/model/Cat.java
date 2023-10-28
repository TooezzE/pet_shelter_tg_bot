package pet.shelter.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Cat class
 */
@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private int birthday;
    @Column(name = "description")
    private String descriptionOfThePet;

    public Cat(Long id,String name, String breed, int birthday, String descriptionOfThePet) {
        this.name = name;
        this.breed = breed;
        this.birthday = birthday;
        this.descriptionOfThePet = descriptionOfThePet;
        this.id=id;
    }

    public Cat() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionOfThePet() {
        return descriptionOfThePet;
    }

    public void setDescriptionOfThePet(String descriptionOfThePet) {
        this.descriptionOfThePet = descriptionOfThePet;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return birthday == cat.birthday && Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(breed, cat.breed) && Objects.equals(descriptionOfThePet, cat.descriptionOfThePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, birthday, descriptionOfThePet);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animalBreed='" + breed + '\'' +
                ", yearOfBirth=" + birthday +
                ", descriptionOfThePet='" + descriptionOfThePet + '\'' +
                '}';
    }
}
