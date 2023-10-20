package pet.shelter.exceptions;

public class DogAdoptersNotFoundException extends RuntimeException{

    public DogAdoptersNotFoundException(String message) {
        super(message);
    }
}
