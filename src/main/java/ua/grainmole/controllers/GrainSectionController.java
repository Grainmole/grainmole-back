package ua.grainmole.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.grainmole.dto.GrainSectionDto;
import ua.grainmole.dto.TermoSectionDto;
import ua.grainmole.requests.GrainSectionRequest;
import ua.grainmole.services.GrainSectionService;
import ua.grainmole.services.TermoSectionService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/grain-sections")
@RequiredArgsConstructor
public class GrainSectionController {
    private final GrainSectionService grainSectionService;
    private final TermoSectionService termoSectionService;

    @PostMapping
    public ResponseEntity<GrainSectionDto> create(
            @RequestBody GrainSectionRequest request) {
        return ResponseEntity.ok(grainSectionService.create(request));
    }

    @PatchMapping("/{grainSectionId}")
    public ResponseEntity<GrainSectionDto> updateStorageId(
            @PathVariable BigInteger grainSectionId,
            @RequestParam Integer storageId) {
        return ResponseEntity.ok(grainSectionService.updateStorageId(grainSectionId, storageId));
    }

    @DeleteMapping("/{grainSectionId}")
    public ResponseEntity<?> deleteGrainSection(@PathVariable BigInteger grainSectionId) {
        grainSectionService.deleteGrainSection(grainSectionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{grainSectionId}/termo-sections")
    public ResponseEntity<List<List<TermoSectionDto>>> getDataForGraphic(
            @PathVariable BigInteger grainSectionId,
            @RequestParam String timestamp) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime date = dateTimeFormat.parse(timestamp, LocalDateTime::from);
        return ResponseEntity.ok(termoSectionService.getAllTermoSectionsByGrainSection(grainSectionId,date));
    }
}
