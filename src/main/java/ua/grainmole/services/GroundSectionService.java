package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.exceptions.CurrentAuditException;
import ua.grainmole.models.GroundSection;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GroundSectionRepository;
import ua.grainmole.requests.CreateGroundSection;
import ua.grainmole.responses.GroundSectionsResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroundSectionService {

    private final GroundSectionRepository groundSectionRepository;
    private final ApplicationAuditAware auditAware;
    private final UserService userService;

    public GroundSection createGroundSection
            (CreateGroundSection createGroundSection) {
        Optional<Integer> userId = auditAware.getCurrentAuditor();
        if (userId.isPresent()) {
            User currentUser = userService.getUserById(userId.get());
            return groundSectionRepository.save(GroundSection.builder()
                    .id(createGroundSection.gatewayId())
                    .user(currentUser)
                    .build());
        } else {
            throw new CurrentAuditException("Does not found authenticated user.");
        }
    }

    public GroundSectionsResponse getAllGroundSectionForUser() {
        Optional<Integer> userId = auditAware.getCurrentAuditor();
        if (userId.isPresent()) {
            User currentUser = userService.getUserById(userId.get());
            return GroundSectionsResponse
                    .builder()
                    .groundSections(
                            groundSectionRepository
                                    .getGroundSectionByUser(currentUser)
                    )
                    .build();
        } else {
            throw new CurrentAuditException("Does not found authenticated user.");
        }
    }
}
