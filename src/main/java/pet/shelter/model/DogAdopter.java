package pet.shelter.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * Dog's adopter class
 **/
@Entity
@Table(name = "dog_adopters")
public class DogAdopter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private int birthday;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "chat_id")
    private Long chatId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    public DogAdopter(Long id,String name, int birthday, String phoneNumber, String email, String address, Long chatId, Dog dog) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.chatId = chatId;
        this.dog = dog;
    }

    public DogAdopter() {
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

    public int getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogAdopter dogAdopter = (DogAdopter) o;
        return Objects.equals(id, dogAdopter.id) && Objects.equals(name, dogAdopter.name) && Objects.equals(chatId, dogAdopter.chatId) && Objects.equals(dog, dogAdopter.dog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatId, dog);
    }

    @Override
    public String toString() {
        return "DogOwners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatId=" + chatId +
                ", dog=" + dog +
                '}';
    }
}
