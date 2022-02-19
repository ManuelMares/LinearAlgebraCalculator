package Classes.Exceptions;

public class InvalidAnswerException extends Exception{
    public InvalidAnswerException(){
        super("\nInvalid answer: It does not match the required characteristics.");
    }
    public InvalidAnswerException(String message){
        super("\nInvalid answer: It does not match the required characteristics. \n" + message);
    }
}