package ua.grainmole.responses;

import lombok.Builder;
import ua.grainmole.dto.GroundSectionDto;

import java.util.List;

@Builder
public record GroundSectionsListResponse(List<GroundSectionDto> groundSections) {
}
