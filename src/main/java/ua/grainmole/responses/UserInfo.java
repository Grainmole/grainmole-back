package ua.grainmole.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record UserInfo (String email , String firstName , String lastName) {}