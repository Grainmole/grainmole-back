package ua.grainmole.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grainmole.dto.GroundSectionDto;
import ua.grainmole.requests.GroundSectionRequest;
import ua.grainmole.services.GroundSectionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ground-sections")
public class GroundSectionController {

    private final GroundSectionService groundSectionService;

    @PostMapping
    public ResponseEntity<GroundSectionDto> createGroundSection(
            @RequestBody GroundSectionRequest createGroundSection) {
        return ResponseEntity.ok(groundSectionService.createGroundSection(createGroundSection));
    }
}
