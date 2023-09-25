package grupo.artifact.exception.custom;

public class NotCreatedException extends RuntimeException{
    
    private static final String DESCRIPTION = "Invaled credentials (401)";

    public NotCreatedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }

}
