package pet.shelter.exceptions;

public class DogAdoptersNotFoundException extends RuntimeException{

    public DogAdoptersNotFoundException() {
    }

    public DogAdoptersNotFoundException(String message) {
        super(message);
    }

    public DogAdoptersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DogAdoptersNotFoundException(Throwable cause) {
        super(cause);
    }

    public DogAdoptersNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
