package pet.shelter.exceptions;

public class CatOwnersNotFoundException extends RuntimeException{

    public CatOwnersNotFoundException(String message) {
        super(message);
    }
}
