package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record InvitaUtenteRequest(

        @NotNull(message = "L'ID è obbligatorio")
        long userInvitatoID,
        @NotNull(message = "L'ID è obbligatorio")
        long capoID,
        @NotNull(message = "L'ID è obbligatorio")
        long teamID
) {
}
