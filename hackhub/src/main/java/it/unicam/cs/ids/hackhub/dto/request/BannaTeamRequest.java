package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record BannaTeamRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long teamID,
        @NotNull(message = "L'ID è obbligatorio")
        Long organizzatoreID
) {
}
