package ua.grainmole.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grainmole.repsonses.UserInfo;
import ua.grainmole.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseEntity<UserInfo> getUserInformation(Principal principal) {
        UserInfo info = userService.getInfoAboutUser(principal);
        return ResponseEntity.ok(info);
    }
}
