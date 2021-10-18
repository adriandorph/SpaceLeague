package Exceptions;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException(){
        super("Method has not been implemented");
    }
}
