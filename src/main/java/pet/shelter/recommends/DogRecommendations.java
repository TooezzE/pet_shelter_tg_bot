package pet.shelter.recommends;

import lombok.*;

@Getter
@Setter
@ToString
public class DogRecommendations extends Recommendations {

    private String firstTimeDogHandlerAdvices;

    public DogRecommendations(String transport,
                              String homeImprovementForYoungPet,
                              String homeImprovementForAdultPet,
                              String recommendationsForDisabledPet,
                              String firstTimeDogHandlerAdvices) {
        super(transport, homeImprovementForYoungPet, homeImprovementForAdultPet, recommendationsForDisabledPet);
        this.firstTimeDogHandlerAdvices = firstTimeDogHandlerAdvices;
    }

    public String getFirstTimeDogHandlerAdvices() {
        return firstTimeDogHandlerAdvices;
    }

    public void setFirstTimeDogHandlerAdvices(String firstTimeDogHandlerAdvices) {
        this.firstTimeDogHandlerAdvices = firstTimeDogHandlerAdvices;
    }

    @Override
    public String toString() {
        return "DogRecommendations{" +
                "firstTimeDogHandlerAdvices='" + firstTimeDogHandlerAdvices + '\'' +
                '}';
    }
}
