package pet.shelter.exceptions;

public class CatAdoptersNotFoundException extends RuntimeException{
    public CatAdoptersNotFoundException() {
    }

    public CatAdoptersNotFoundException(String message) {
        super(message);
    }

    public CatAdoptersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatAdoptersNotFoundException(Throwable cause) {
        super(cause);
    }

    public CatAdoptersNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
