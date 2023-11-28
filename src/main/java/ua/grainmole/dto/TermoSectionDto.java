package ua.grainmole.dto;

import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
public record TermoSectionDto(Integer id,
                              Integer heightLevel ,
                              Float temperature ,
                              String time ,
                              BigInteger grainSectionId) {
}
