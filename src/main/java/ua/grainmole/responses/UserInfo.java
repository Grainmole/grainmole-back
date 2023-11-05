package ua.grainmole.responses;

import lombok.Builder;

@Builder
public record UserInfo(String email, String firstName, String lastName) {
}