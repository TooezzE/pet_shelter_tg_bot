package pet.shelter.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.shelter.command.ShelterType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Класс для отслеживания состояния пользователя в приюте
 */
@Entity
public class UserStatus {

    @Id
    private Long chatId;
    @Enumerated
    private ShelterType shelterType;
    @OneToOne
    private CatOwners catOwner;
    @OneToOne
    private DogOwners dogOwner;

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

    public CatOwners getCatOwner() {
        return catOwner;
    }

    public void setCatOwner(CatOwners catOwner) {
        this.catOwner = catOwner;
    }

    public DogOwners getDogOwner() {
        return dogOwner;
    }

    public void setDogOwner(DogOwners dogOwner) {
        this.dogOwner = dogOwner;
    }
}
