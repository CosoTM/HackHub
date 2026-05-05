package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PenalizzaTeamRequest(
        @NotNull(message = "L'ID dello staff è obbligatorio")
        long staffID,

        @NotNull(message = "L'ID del team penalizzato è obbligatorio")
        long teamID,

        @NotNull(message = "la penalizzazione è obbligatoria")
        int penalizzazione ) {
}
