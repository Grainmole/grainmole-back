package ua.grainmole.exceptions;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CurrentAuditException extends RuntimeException {
}
