package grupo.artifact.exception.custom;

public class NotModifiedException extends RuntimeException{
    private static final String DESCRIPTION = "Modify Error (401)";

    public NotModifiedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
