package massimomauro.S6L5DevelopmentSpringWebService.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
