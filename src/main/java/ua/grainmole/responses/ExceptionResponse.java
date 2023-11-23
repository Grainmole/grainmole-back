package ua.grainmole.responses;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(String message, LocalDateTime timestamp, HttpStatus httpStatus , String path) {
}
