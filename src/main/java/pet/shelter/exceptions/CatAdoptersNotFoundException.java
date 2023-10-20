package pet.shelter.exceptions;

public class CatAdoptersNotFoundException extends RuntimeException{

    public CatAdoptersNotFoundException(String message) {
        super(message);
    }
}
