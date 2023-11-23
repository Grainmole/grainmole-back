package ua.grainmole.dto;

import lombok.Builder;

import java.math.BigInteger;
import java.sql.Timestamp;

@Builder
public record TermoSectionDto(Integer id,
                              Integer heightLevel ,
                              Float temperature ,
                              Timestamp time ,
                              BigInteger grainSectionId) {
}
