package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.GrainSectionDto;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.mappers.GroundSectionDtoMapper;
import ua.grainmole.models.GrainSection;
import ua.grainmole.models.GroundSection;
import ua.grainmole.models.Storage;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GrainSectionRepository;
import ua.grainmole.repositories.GroundSectionRepository;
import ua.grainmole.repositories.StorageRepository;
import ua.grainmole.requests.GroundSectionRequest;
import ua.grainmole.services.ApplicationAuditAware;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroundSectionService {

    private final GroundSectionRepository groundSectionRepository;
    private final GrainSectionRepository grainSectionRepository;
    private final StorageRepository storageRepository;
    private final GroundSectionDtoMapper dtoMapper;
    private final ApplicationAuditAware auditAware;

    public GroundSectionDto createGroundSection
            (GroundSectionRequest createGroundSection) {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        GroundSection groundSection =
                groundSectionRepository.save(new GroundSection(createGroundSection.gatewayId(), currentUser));
        return dtoMapper.mapEntityToDto(groundSection);
    }

    public List<GroundSectionDto> getAllGroundSectionForUser() {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        return groundSectionRepository
                .getGroundSectionByUser(currentUser)
                .stream().map(dtoMapper::mapEntityToDto).toList();
    }

    public List<GrainSectionDto> getAllGrainSectionForGroundSection(BigInteger groundSectionId) {
        User crrentUser = groundSectionRepository.getReferenceById(groundSectionId).getUser();
        List<Storage> storages = storageRepository.findAllByUser(crrentUser);
        List<GrainSection> sections = new ArrayList<>();
        for (Storage storage : storages) {
            sections.addAll(grainSectionRepository.getGrainSectionsByStorage(storage));
        }
        return sections.stream().map(g -> GrainSectionDto.builder()
                .id(g.getId())
                .airTemperature(g.getAirTemperature())
                .airHumidity(g.getAirHumidity())
                .batteryLevel(g.getBatteryLevel())
                .storageId(g.getStorage().getId())
                .build()).toList();
    }
}
