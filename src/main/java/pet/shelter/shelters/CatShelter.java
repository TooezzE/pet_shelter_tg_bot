package pet.shelter.shelters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pet.shelter.pets.Cat;
import pet.shelter.pets.Dog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashMap;
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
    private HashMap<String, String> allRecommendations;
    @Column(name = "handler_advices")
    private String catHandlerAdvices;
    @Column(name = "handlers")
    private String handlersList;

    public CatShelter(List<Cat> catsList,
                      String infoAboutShelter,
                      String howToPickUpAPet,
                      String timetable,
                      String address,
                      String drivingDirections,
                      String securityInfo,
                      String safetyPrecautions,
                      String rulesToMeetingAnimal,
                      String documents,
                      HashMap<String, String> allRecommendations,
                      String catHandlerAdvices,
                      String handlersList) {
        this.catsList = catsList;
        this.infoAboutShelter = infoAboutShelter;
        this.howToPickUpAPet = howToPickUpAPet;
        this.timetable = timetable;
        this.address = address;
        this.drivingDirections = drivingDirections;
        this.securityInfo = securityInfo;
        this.safetyPrecautions = safetyPrecautions;
        this.rulesToMeetingAnimal = rulesToMeetingAnimal;
        this.documents = documents;
        this.allRecommendations = allRecommendations;
        this.catHandlerAdvices = catHandlerAdvices;
        this.handlersList = handlersList;
    }
}
