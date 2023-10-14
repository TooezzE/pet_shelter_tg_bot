package pet.shelter.entity.pets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Animal {

    private String name;
    private int age;
    private String breed;
    private boolean isDisabled;
    private boolean isAdopted;//взять из приюта

    public Animal(String name,

                  int age,
                  String breed,
                  boolean isDisabled,
                  boolean isAdepted) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.isDisabled = isDisabled;
        this.isAdopted = isAdepted;
    }

    public Animal() {

    }
}
