package pet.shelter.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * Класс для владельцев собак
 **/
@Entity
@Table(name = "dog_owners")
public class DogOwners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "chat_id")
    private Long chatId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    public DogOwners(Long id, String name, Long chatId) {
        this.id = id;
        this.name = name;
        this.chatId = chatId;
    }

    public DogOwners(Long id, String name, int yearOfBirth, String phoneNumber, String mail, String address, Long chatId, Dog dog) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.address = address;
        this.chatId = chatId;
        this.dog = dog;
    }

    public DogOwners() {
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        DogOwners dogOwners = (DogOwners) o;
        return Objects.equals(id, dogOwners.id) && Objects.equals(name, dogOwners.name) && Objects.equals(chatId, dogOwners.chatId) && Objects.equals(dog, dogOwners.dog);
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
