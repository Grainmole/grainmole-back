package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.exceptions.CurrentAuditException;
import ua.grainmole.models.GroundSection;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GroundSectionRepository;
import ua.grainmole.repositories.UserRepository;
import ua.grainmole.requests.CreateGroundSection;
import ua.grainmole.responses.GroundSectionsListResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroundSectionService {

    private final GroundSectionRepository groundSectionRepository;
    private final ApplicationAuditAware auditAware;
    private final UserRepository userRepository;

    public GroundSectionDto createGroundSection
            (CreateGroundSection createGroundSection) {
        Optional<Integer> userId = auditAware.getCurrentAuditor();
        if (userId.isPresent()) {
            User currentUser = userRepository.getReferenceById(userId.get());
            groundSectionRepository.save(new GroundSection(createGroundSection.gatewayId(), currentUser));
            return new GroundSectionDto(createGroundSection.gatewayId(), currentUser.getId());
        } else {
            throw new CurrentAuditException("Does not found authenticated user.");
        }
    }

    public GroundSectionsListResponse getAllGroundSectionForUser() {
        Optional<Integer> userId = auditAware.getCurrentAuditor();
        if (userId.isPresent()) {
            User currentUser = userRepository.getReferenceById(userId.get());
            return GroundSectionsListResponse
                    .builder()
                    .groundSections(groundSectionRepository
                            .getGroundSectionByUser(currentUser)
                            .stream().map(groundSection -> new GroundSectionDto(
                                    groundSection.getId(), currentUser.getId()
                            )).toList())
                    .build();
        } else {
            throw new CurrentAuditException("Does not found authenticated user.");
        }
    }
}
