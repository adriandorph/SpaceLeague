package Exceptions;

public class UnfairException extends Exception{
    public UnfairException(){
        super("Unfair match: Ships not in same class");
    }
}
