package ua.grainmole.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record CreateGroundSection(Integer gatewayId){}
