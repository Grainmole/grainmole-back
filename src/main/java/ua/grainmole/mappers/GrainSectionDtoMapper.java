package ua.grainmole.mappers;

import org.springframework.stereotype.Service;
import ua.grainmole.dto.GrainSectionDto;
import ua.grainmole.models.GrainSection;

@Service
public class GrainSectionDtoMapper implements BasicEntityMapper <GrainSection, GrainSectionDto> {
    @Override
    public GrainSectionDto mapEntityToDto(GrainSection grainSection) {
        return GrainSectionDto.builder()
                .id(grainSection.getId())
                .batteryLevel(grainSection.getBatteryLevel())
                .airTemperature(grainSection.getAirTemperature())
                .airHumidity(grainSection.getAirHumidity())
                .storageId(grainSection.getStorage().getId())
                .build();
    }
}
