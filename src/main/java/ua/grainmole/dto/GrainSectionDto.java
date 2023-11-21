package ua.grainmole.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record GrainSectionDto(BigInteger id,
                              float batteryLevel,
                              float airTemperature,
                              float airHumidity,
                              Integer storageId) {
}
