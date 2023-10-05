package grupo.artifact.exception.custom;

public class NotFoundException extends RuntimeException{
    
    private static final String DESCRIPTION = "Error in modify (404)";

    public NotFoundException(String detail){
        super(DESCRIPTION + ". " + detail);
    }

}