package pet.shelter.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Cat's adopter class
 **/
@Entity
@Table(name = "cat_adopters")
public class CatAdopter {

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
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat cat;

    public CatAdopter(Long id,String name, int birthday, String phoneNumber, String email, String address, Long chatId, Cat cat) {
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.chatId = chatId;
        this.cat = cat;
        this.id = id;
    }

    public CatAdopter() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatAdopter catAdopter = (CatAdopter) o;
        return Objects.equals(id, catAdopter.id) && Objects.equals(name, catAdopter.name) && Objects.equals(chatId, catAdopter.chatId) && Objects.equals(cat, catAdopter.cat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatId, cat);
    }

    @Override
    public String toString() {
        return "CatOwners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatId=" + chatId +
                ", cat=" + cat +
                '}';
    }
}
