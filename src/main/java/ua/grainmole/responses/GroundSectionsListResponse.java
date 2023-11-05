package ua.grainmole.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.grainmole.models.GroundSection;

import java.util.List;

@Builder
public record GroundSectionsResponse(List<GroundSection> groundSections) {
}
