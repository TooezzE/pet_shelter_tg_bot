package pet.shelter.recommends;


public abstract class Recommendations {

    private String transport;
    private String homeImprovementForYoungPet;
    private String homeImprovementForAdultPet;
    private String recommendationsForDisabledPet;

    public Recommendations(String transport,
                           String homeImprovementForYoungPet,
                           String homeImprovementForAdultPet,
                           String recommendationsForDisabledPet) {
        this.transport = transport;
        this.homeImprovementForYoungPet = homeImprovementForYoungPet;
        this.homeImprovementForAdultPet = homeImprovementForAdultPet;
        this.recommendationsForDisabledPet = recommendationsForDisabledPet;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getHomeImprovementForYoungPet() {
        return homeImprovementForYoungPet;
    }

    public void setHomeImprovementForYoungPet(String homeImprovementForYoungPet) {
        this.homeImprovementForYoungPet = homeImprovementForYoungPet;
    }

    public String getHomeImprovementForAdultPet() {
        return homeImprovementForAdultPet;
    }

    public void setHomeImprovementForAdultPet(String homeImprovementForAdultPet) {
        this.homeImprovementForAdultPet = homeImprovementForAdultPet;
    }

    public String getRecommendationsForDisabledPet() {
        return recommendationsForDisabledPet;
    }

    public void setRecommendationsForDisabledPet(String recommendationsForDisabledPet) {
        this.recommendationsForDisabledPet = recommendationsForDisabledPet;
    }

    @Override
    public String toString() {
        return "Recommendations{" +
                "transport='" + transport + '\'' +
                ", homeImprovementForYoungPet='" + homeImprovementForYoungPet + '\'' +
                ", homeImprovementForAdultPet='" + homeImprovementForAdultPet + '\'' +
                ", recommendationsForDisabledPet='" + recommendationsForDisabledPet + '\'' +
                '}';
    }
}
