package pet.shelter.shelters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pet.shelter.pets.Cat;
import pet.shelter.pets.Dog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cat_shelter")
public class CatShelter {
    @JsonIgnore
    @OneToMany(targetEntity = Cat.class)
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
    @Column(name = "recommendations")
    private List<String> allRecommendations;
    @Column(name = "handler_advices")
    private String catHandlerAdvices;
    @Column(name = "handlers")
    private String handlersList;

}
