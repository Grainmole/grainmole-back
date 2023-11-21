package ua.grainmole.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.GrainSectionDto;
import ua.grainmole.dto.StorageDto;
import ua.grainmole.exceptions.PermissionDeniedException;
import ua.grainmole.mappers.StorageDtoMapper;
import ua.grainmole.models.SeedType;
import ua.grainmole.models.Storage;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GrainSectionRepository;
import ua.grainmole.repositories.StorageRepository;
import ua.grainmole.requests.StorageRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final ApplicationAuditAware auditAware;
    private final SeedTypeService seedTypeService;
    private final StorageDtoMapper dtoMapper;
    private final GrainSectionRepository grainSectionRepository;

    public StorageDto createStorage(StorageRequest storageRequest) {
        User user = auditAware.returnCurrentAuthenticatedUser();
        SeedType seedType = seedTypeService.returnSeedTypeByName(storageRequest.seedTypesName());
        Storage currentStorage = storageRepository.save(new Storage(
                null
                , storageRequest.name(),
                user,
                seedType));
        return dtoMapper.mapEntityToDto(currentStorage);
    }

    public StorageDto updateStorage(Integer storageId, StorageRequest storageRequest) {
        User user = auditAware.returnCurrentAuthenticatedUser();
        SeedType seedType = seedTypeService.returnSeedTypeByName(storageRequest.seedTypesName());
        if (doesStorageExistById(storageId)) {
            if (checkIfUserHasThatStorage(storageId, user)) {
                Storage currentStorage = storageRepository.save(new Storage(
                        storageId,
                        storageRequest.name(),
                        user,
                        seedType));
                return dtoMapper.mapEntityToDto(currentStorage);
            } else {
                throw new PermissionDeniedException(
                        "You do not have permission to update other user's storages");
            }
        } else {
            throw new EntityNotFoundException("Not found such storage by id: " + storageId);
        }
    }

    public List<StorageDto> getAllStoragesForUser() {
        User user = auditAware.returnCurrentAuthenticatedUser();
        return storageRepository.findAllByUser(user)
                .stream()
                .map(dtoMapper::mapEntityToDto)
                .toList();
    }

    public List<GrainSectionDto> getAllGrainSectionForCertainStorage(Integer storageId) {
        User user = auditAware.returnCurrentAuthenticatedUser();
        Storage storage = storageRepository.getReferenceById(storageId);
        if (!checkIfUserHasThatStorage(storageId, user)) {
            throw new PermissionDeniedException("You do not have permission to this storages");
        }
        return grainSectionRepository.getGrainSectionsByStorage(storage)
                .stream()
                .map(g -> GrainSectionDto.builder()
                        .id(g.getId())
                        .batteryLevel(g.getBatteryLevel())
                        .airHumidity(g.getAirHumidity())
                        .airTemperature(g.getAirTemperature())
                        .storageId(g.getStorage().getId()).build()).toList();
    }

    public boolean checkIfUserHasThatStorage(Integer storageId, User user) {
        return storageRepository.getReferenceById(storageId).getUser().equals(user);
    }

    private boolean doesStorageExistById(Integer storageId) {
        Optional<Storage> optionalStorage = storageRepository.findById(storageId);
        return optionalStorage.isPresent();
    }
}
