package amazons.figures;

public class IllegalMoveException extends Exception{
    private final String message;

    public IllegalMoveException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
