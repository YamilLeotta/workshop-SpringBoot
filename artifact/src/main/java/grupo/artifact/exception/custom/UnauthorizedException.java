package grupo.artifact.exception.custom;

public class UnauthorizedException extends RuntimeException{
    
    private static final String DESCRIPTION = "Error in create (400)";

    public UnauthorizedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }

}