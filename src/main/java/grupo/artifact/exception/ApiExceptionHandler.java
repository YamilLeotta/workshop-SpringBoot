package grupo.artifact.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import grupo.artifact.exception.custom.EmptyElementException;
import grupo.artifact.exception.custom.NotCreatedException;
import grupo.artifact.exception.custom.NotFoundException;
import grupo.artifact.exception.custom.NotModifiedException;
import grupo.artifact.exception.custom.UnauthorizedException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        EmptyElementException.class,
        NotCreatedException.class,
        NotModifiedException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        NotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
        UnauthorizedException.class,
    })
    @ResponseBody
    public void unauthorized(){
        // Empty, because http in case 401 not supported body response
    }
}
