package grupo.artifact.exception.custom;

public class InvalidDataException extends RuntimeException{
    private static final String DESCRIPTION = "Invalid Data (400)";

    public InvalidDataException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
