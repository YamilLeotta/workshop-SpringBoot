package grupo.artifact.exception.custom;

public class NotFoundException extends RuntimeException{
    
    private static final String DESCRIPTION = "Modify Error (404)";

    public NotFoundException(String detail){
        super(DESCRIPTION + ". " + detail);
    }

}