package ua.grainmole.converters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeConverter {

    public String convertToString(LocalDateTime time) {
        return time.withSecond(0).withNano(0).toString();
    }
}
