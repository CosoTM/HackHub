package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record IscriviTeamRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long capoID,
        @NotNull(message = "L'ID è obbligatorio")
        Long teamID
) {
}
