package pet.shelter.entity.shelters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.pets.Cat;
import pet.shelter.entity.pets.Dog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dog_shelter")
@Getter
@Setter
@ToString
public class DogShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private List<Dog> dogsList;
    @Column(name = "info")
    private String infoAboutShelter;
    @Column(name = "how_to_pick_up")
    private String howToPickUpAPet;
    @Column(name = "timetable")
    private String timetable;
    @Column(name = "address")
    private String address;
    @Column(name = "driving_directions")
    private String drivingDirections;
    @Column(name = "security_info")
    private String securityInfo;
    @Column(name = "safety_precautions")
    private String safetyPrecautions;
    @Column(name = "rules_to_meet_animal")
    private String rulesToMeetingAnimal;
    @Column(name = "documents")
    private String documents;
    @Column(name = "handlers")
    private String handlersList;

    public DogShelter(List<Dog> dogsList,
                      String infoAboutShelter,
                      String howToPickUpAPet,
                      String timetable,
                      String address,
                      String drivingDirections,
                      String securityInfo,
                      String safetyPrecautions,
                      String rulesToMeetingAnimal,
                      String documents,
                      String handlersList) {
        this.dogsList = dogsList;
        this.infoAboutShelter = infoAboutShelter;
        this.howToPickUpAPet = howToPickUpAPet;
        this.timetable = timetable;
        this.address = address;
        this.drivingDirections = drivingDirections;
        this.securityInfo = securityInfo;
        this.safetyPrecautions = safetyPrecautions;
        this.rulesToMeetingAnimal = rulesToMeetingAnimal;
        this.documents = documents;
        this.handlersList = handlersList;
    }

    public DogShelter() {
    }
}
