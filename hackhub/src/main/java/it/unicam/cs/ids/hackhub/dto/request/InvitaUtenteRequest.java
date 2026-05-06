package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record InvitaUtenteRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long userInvitatoID,
        @NotNull(message = "L'ID è obbligatorio")
        Long capoID,
        @NotNull(message = "L'ID è obbligatorio")
        Long teamID
) {
}
