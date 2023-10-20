package pet.shelter.exceptions;

public class DogOwnersNotFoundException extends RuntimeException{

    public DogOwnersNotFoundException(String message) {
        super(message);
    }
}
