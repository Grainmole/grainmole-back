package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.exceptions.CurrentAuditException;
import ua.grainmole.models.User;
import ua.grainmole.repositories.UserRepository;
import ua.grainmole.responses.UserInfoResponse;
import ua.grainmole.services.ApplicationAuditAware;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ApplicationAuditAware auditAware;
    private final UserRepository userRepository;

    public UserInfoResponse getInfoAboutUser() {
        Optional<Integer> auditor = auditAware.getCurrentAuditor();
        if (auditor.isPresent()) {
            User currentUser = userRepository.getReferenceById(auditor.get());
            return UserInfoResponse.builder()
                    .firstName(currentUser.getFirstname())
                    .lastName(currentUser.getLastname())
                    .email(currentUser.getEmail())
                    .build();
        } else {
            throw new CurrentAuditException("Does not found authenticated user");
        }
    }
}
