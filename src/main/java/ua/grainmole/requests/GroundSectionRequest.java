package ua.grainmole.requests;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record GroundSectionRequest(BigInteger gatewayId) {
}
