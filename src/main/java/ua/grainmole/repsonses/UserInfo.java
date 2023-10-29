package ua.grainmole.repsonses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public record UserInfo (String email , String firstName , String lastName) {}
