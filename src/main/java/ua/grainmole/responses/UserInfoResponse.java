package ua.grainmole.responses;

import lombok.Builder;

@Builder
public record UserInfoResponse(String email, String firstName, String lastName) {
}