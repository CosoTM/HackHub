package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreaTeamRequest(
        @NotNull(message = "L'ID è obbligatorio")
        long userID,
        @NotBlank(message = "Il nome del team è obbligatorio")
        String nomeTeam) {

}
