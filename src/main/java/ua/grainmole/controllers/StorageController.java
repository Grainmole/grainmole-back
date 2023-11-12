package ua.grainmole.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grainmole.dto.StorageDto;
import ua.grainmole.requests.StorageRequest;
import ua.grainmole.services.StorageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storages")
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageDto> createStorage(
            @RequestBody StorageRequest request) {
        return ResponseEntity.ok(storageService.createStorage(request));
    }

    @PutMapping("/{storageId}")
    public ResponseEntity<StorageDto> updateStorage(
            @PathVariable Integer storageId,
            @RequestBody StorageRequest storageRequest) {
        return ResponseEntity.ok(storageService.updateStorage(storageId, storageRequest));
    }
}
