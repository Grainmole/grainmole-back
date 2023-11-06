package ua.grainmole.mappers;

import org.springframework.stereotype.Service;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.models.GroundSection;

@Service
public class GroundSectionDtoMapper implements BasicEntityMapper<GroundSection, GroundSectionDto> {
    @Override
    public GroundSectionDto mapToDto(GroundSection groundSection) {
        return GroundSectionDto.builder()
                .groundSectionId(groundSection.getId())
                .userId(groundSection.getUser().getId())
                .build();
    }
}
