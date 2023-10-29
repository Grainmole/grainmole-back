package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.grainmole.models.User;
import ua.grainmole.repositories.UserRepository;
import ua.grainmole.repsonses.UserInfo;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserInfo getInfoAboutUser(Principal principal) {
        Optional<User> loggedUser = userRepository.findByEmail(principal.getName());
        if (loggedUser.isPresent()) {
            User present = loggedUser.get();
            return UserInfo.builder()
                    .email(present.getEmail())
                    .firstName(present.getFirstname())
                    .lastName(present.getLastname())
                    .build();
        } else {
            throw new UsernameNotFoundException("Does not found authenticated user by this email.");
        }
    }
}
