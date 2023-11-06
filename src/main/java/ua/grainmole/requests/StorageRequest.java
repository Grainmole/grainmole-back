package ua.grainmole.requests;

import lombok.Builder;

@Builder
public record StorageRequest(String name,
                            String seedTypesName) {
}
