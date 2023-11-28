package ua.grainmole.responses;

import lombok.Builder;
import org.springframework.http.HttpStatus;


@Builder
public record ExceptionResponse(String message, String timestamp, HttpStatus httpStatus , String path) {
}
