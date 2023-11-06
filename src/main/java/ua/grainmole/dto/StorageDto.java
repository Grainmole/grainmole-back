package ua.grainmole.dto;

import lombok.Builder;

@Builder
public record StorageDto(Integer storageId,
                         String name,
                         Integer userId,
                         String seedTypesName) {
}
