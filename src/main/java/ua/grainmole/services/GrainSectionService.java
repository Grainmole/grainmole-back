package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.GrainSectionDto;
import ua.grainmole.exceptions.PermissionDeniedException;
import ua.grainmole.models.GrainSection;
import ua.grainmole.models.Storage;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GrainSectionRepository;
import ua.grainmole.repositories.StorageRepository;
import ua.grainmole.requests.GrainSectionRequest;

import java.math.BigInteger;


@Service
@RequiredArgsConstructor
public class GrainSectionService {
    private final GrainSectionRepository grainSectionRepository;
    private final ApplicationAuditAware auditAware;
    private final StorageRepository storageRepository;
    private final StorageService storageService;

    public GrainSectionDto create(GrainSectionRequest request) {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        Storage storage = storageRepository.getReferenceById(request.storageId());
        if (!storageService.checkIfUserHasThatStorage(request.storageId(), currentUser)) {
            throw new PermissionDeniedException("This user have not such storage");
        }
        grainSectionRepository.save(new GrainSection(
                request.id(), 100.0F, 0.0F, 0.0F, storage));
        return GrainSectionDto.builder()
                .id(request.id())
                .batteryLevel(100F)
                .storageId(storage.getId())
                .build();
    }

    public GrainSectionDto updateStorageId(BigInteger grainSectionId, Integer storageId) {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        GrainSection grainSection = grainSectionRepository.getReferenceById(grainSectionId);
        Storage storage = storageRepository.getReferenceById(storageId);
        grainSection.setStorage(storage);
        grainSectionRepository.save(grainSection);
        if (!storageService.checkIfUserHasThatStorage(storageId, currentUser)) {
            throw new PermissionDeniedException("This user have not such storage");
        }
        return GrainSectionDto.builder()
                .id(grainSection.getId())
                .storageId(storageId)
                .airTemperature(grainSection.getAirTemperature())
                .airHumidity(grainSection.getAirHumidity())
                .batteryLevel(grainSection.getBatteryLevel())
                .build();
    }

    public void deleteGrainSection(BigInteger grainSectionId) {
        GrainSection grainSection = grainSectionRepository.getReferenceById(grainSectionId);
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        if (!grainSection.getStorage().getUser().equals(currentUser)) {
            throw new PermissionDeniedException("This user have not such device");
        }
        grainSectionRepository.delete(grainSection);
    }
}
