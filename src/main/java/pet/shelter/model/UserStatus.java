package pet.shelter.model;

import pet.shelter.command.ShelterType;

import javax.persistence.*;

/**
 * Class to check user state in shelter
 */
@Entity
@Table(name = "user_status")
public class UserStatus {

    @Id
    @Column(name = "chat_id")
    private Long chatId;
    @Enumerated(EnumType.STRING)
    @Column(name = "shelter_type")
    private ShelterType shelterType;
    @OneToOne
    private CatAdopter catAdopter;
    @OneToOne
    private DogAdopter dogAdopter;

    public UserStatus(Long chatId, ShelterType shelterType) {
        this.chatId = chatId;
        this.shelterType = shelterType;
    }

    public UserStatus() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    public CatAdopter getCatAdopter() {
        return catAdopter;
    }

    public void setCatAdopter(CatAdopter catAdopter) {
        this.catAdopter = catAdopter;
    }

    public DogAdopter getDogAdopter() {
        return dogAdopter;
    }

    public void setDogAdopter(DogAdopter dogAdopter) {
        this.dogAdopter = dogAdopter;
    }
}
