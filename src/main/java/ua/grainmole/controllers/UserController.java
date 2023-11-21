package ua.grainmole.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.dto.StorageDto;
import ua.grainmole.responses.UserInfoResponse;
import ua.grainmole.services.GroundSectionService;
import ua.grainmole.services.StorageService;
import ua.grainmole.services.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final StorageService storageService;
    private final GroundSectionService groundSectionService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInformation() {
        return ResponseEntity.ok(userService.getInfoAboutUser());
    }

    @GetMapping("/ground-sections")
    public ResponseEntity<List<GroundSectionDto>> getUsersGroundSections() {
        return ResponseEntity.ok(groundSectionService.getAllGroundSectionForUser());
    }

    @GetMapping("/storages")
    public ResponseEntity<List<StorageDto>> getAllStoragesForUser() {
        return ResponseEntity.ok(storageService.getAllStoragesForUser());
    }
}
