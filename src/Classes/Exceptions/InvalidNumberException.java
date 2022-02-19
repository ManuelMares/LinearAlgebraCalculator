package Classes.Exceptions;

public class InvalidNumberException extends Exception{
    public InvalidNumberException(){
        super("\nInvalid number: It does not match the required characteristics.");
    }
    public InvalidNumberException(String message){
        super("\nInvalid number: It does not match the required characteristics. \n" + message);
    }
}
