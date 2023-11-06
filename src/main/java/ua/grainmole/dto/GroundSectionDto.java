package ua.grainmole.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record GroundSectionDto(BigInteger groundSectionId, Integer userId) {
}
