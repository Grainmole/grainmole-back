package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.mappers.GroundSectionDtoMapper;
import ua.grainmole.models.GroundSection;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GroundSectionRepository;
import ua.grainmole.requests.GroundSectionRequest;
import ua.grainmole.responses.GroundSectionsListResponse;

@Service
@RequiredArgsConstructor
public class GroundSectionService {

    private final GroundSectionRepository groundSectionRepository;
    private final GroundSectionDtoMapper dtoMapper;
    private final ApplicationAuditAware auditAware;

    public GroundSectionDto createGroundSection
            (GroundSectionRequest createGroundSection) {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        GroundSection groundSection =
                groundSectionRepository.save(new GroundSection(createGroundSection.gatewayId(), currentUser));
        return dtoMapper.mapToDto(groundSection);
    }

    public GroundSectionsListResponse getAllGroundSectionForUser() {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        return GroundSectionsListResponse
                .builder()
                .groundSections(groundSectionRepository
                        .getGroundSectionByUser(currentUser)
                        .stream().map(dtoMapper::mapToDto).toList())
                .build();
    }
}
