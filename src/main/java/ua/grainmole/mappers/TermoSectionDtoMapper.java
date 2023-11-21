package ua.grainmole.mappers;

import org.springframework.stereotype.Service;
import ua.grainmole.dto.TermoSectionDto;
import ua.grainmole.models.TermoSection;

@Service
public class TermoSectionDtoMapper implements BasicEntityMapper<TermoSection , TermoSectionDto> {
    @Override
    public TermoSectionDto mapEntityToDto(TermoSection termoSection) {
        return TermoSectionDto.builder()
                .id(termoSection.getId())
                .grainSectionId(termoSection.getGrainSection().getId())
                .heightLevel(termoSection.getHeightLevel())
                .temperature(termoSection.getTemperature())
                .time(termoSection.getTime())
                .build();
    }
}
