package ua.grainmole.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.grainmole.exceptions.CurrentAuditException;
import ua.grainmole.exceptions.PermissionDeniedException;
import ua.grainmole.responses.ExceptionResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = CurrentAuditException.class)
    public ResponseEntity<ExceptionResponse> currentAuditExceptionHandler(
            CurrentAuditException e,
            HttpServletRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .path(request.getRequestURL().toString())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PermissionDeniedException.class)
    public ResponseEntity<ExceptionResponse> permissionDeniedExceptionHandler(
            PermissionDeniedException e,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .message(e.getMessage())
                .path(request.getRequestURL().toString())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.FORBIDDEN);
    }
}
