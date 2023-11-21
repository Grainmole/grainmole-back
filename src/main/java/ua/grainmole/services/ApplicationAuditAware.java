package ua.grainmole.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.grainmole.exceptions.CurrentAuditException;
import ua.grainmole.models.User;
import ua.grainmole.repositories.UserRepository;

import java.util.Optional;

@Component
@NoArgsConstructor
public class ApplicationAuditAware implements AuditorAware<Integer> {
    private UserRepository userRepository;

    @Autowired
    public ApplicationAuditAware(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        User userPrincipal = (User) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }

    public User returnCurrentAuthenticatedUser() {
        Optional<Integer> auditorId = getCurrentAuditor();
        if (auditorId.isPresent()) {
            return userRepository.getReferenceById(auditorId.get());
        } else {
            throw new CurrentAuditException("Does not found such authenticated user.");
        }
    }
}
