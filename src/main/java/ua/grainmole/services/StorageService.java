package ua.grainmole.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.StorageDto;
import ua.grainmole.exceptions.NotHaveSuchRightsException;
import ua.grainmole.mappers.StorageDtoMapper;
import ua.grainmole.models.SeedType;
import ua.grainmole.models.Storage;
import ua.grainmole.models.User;
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

    public StorageDto createStorage(StorageRequest storageRequest) {
        User user = auditAware.returnCurrentAuthenticatedUser();
        SeedType seedType = seedTypeService.returnSeedTypeByName(storageRequest.seedTypesName());
        Storage currentStorage = storageRepository.save(new Storage(
                null
                , storageRequest.name(),
                user,
                seedType));
        return dtoMapper.mapToDto(currentStorage);
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
                return dtoMapper.mapToDto(currentStorage);
            } else {
                throw new NotHaveSuchRightsException(
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
                .map(dtoMapper::mapToDto)
                .toList();
    }

    private boolean checkIfUserHasThatStorage(Integer storageId, User user) {
        return storageRepository.findAllByUser(user).stream()
                .filter(s -> s.getId().equals(storageId)).toList().size() == 1;
    }

    private boolean doesStorageExistById(Integer storageId) {
        Optional<Storage> optionalStorage = storageRepository.findById(storageId);
        return optionalStorage.isPresent();
    }
}
