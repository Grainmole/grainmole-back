package ua.grainmole.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public record CreateGroundSection(Integer gatewayId){}
