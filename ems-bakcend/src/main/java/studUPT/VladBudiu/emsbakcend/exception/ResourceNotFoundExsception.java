package studUPT.VladBudiu.emsbakcend.exception;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundExsception extends RuntimeException {

    public ResourceNotFoundExsception(String message)
    {
        super(message);
    }

}
