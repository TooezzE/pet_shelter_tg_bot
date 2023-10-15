package pet.shelter.entity.pets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Animal {

    private String name;
    private int age;
    private String breed;
    @Column(name = "is_disabled")
    private boolean isDisabled;
    @Column(name = "is_adopted")
    private boolean isAdopted; //взяли ли животное из приюта

    public Animal(String name,
                  int age,
                  String breed,
                  boolean isDisabled,
                  boolean isAdopted) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.isDisabled = isDisabled;
        this.isAdopted = isAdopted;
    }

    public Animal() {

    }
}
