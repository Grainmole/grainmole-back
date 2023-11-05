package ua.grainmole.dto;

import lombok.Builder;

@Builder
public record GroundSectionDto(Integer groundSectionId, Integer userId) {
}
