package pet.shelter.entity.shelters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet.shelter.entity.pets.Cat;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cat_shelter")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CatShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "shelter")
    private List<Cat> catsList;
    @Column(name = "info")
    private String infoAboutShelter;
    @Column(name = "how_pick_up")
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
}
