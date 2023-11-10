package massimomauro.S6L5DevelopmentSpringWebService.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(int id) {
        super(id + " non trovato!");
    }
}
