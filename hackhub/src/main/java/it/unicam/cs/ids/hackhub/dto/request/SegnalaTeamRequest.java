package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SegnalaTeamRequest(
        @NotNull(message = "L'ID è obbligatorio")
        long mentoreID,
        @NotNull(message = "L'ID è obbligatorio")
        long teamID,
        @NotNull(message = "L'ID è obbligatorio")
        long hackathonID,
        @NotBlank(message = "Il motivo della segnalazione è obbligatoria")
        String motivoSegnalazione
) {
}
