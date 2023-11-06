package ua.grainmole.responses;

import ua.grainmole.dto.StorageDto;

import java.util.List;

public record StoragesListResponse(List<StorageDto> userStorages ) {
}
