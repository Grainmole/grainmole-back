package ua.grainmole.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.StorageDto;
import ua.grainmole.models.Storage;


@Service
public class StorageDtoMapper implements BasicEntityMapper<Storage, StorageDto> {

    @Override
    public StorageDto mapEntityToDto(Storage storage) {
        return StorageDto.builder()
                .storageId(storage.getId())
                .name(storage.getName())
                .userId(storage.getUser().getId())
                .seedTypesName(storage.getSeedType().getName())
                .build();
    }
}
