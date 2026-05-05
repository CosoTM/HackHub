package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProclamaVincitoreRequest(
        @NotNull(message = "L'ID è obbligatorio")
        long teamID,
        @NotNull(message = "L'ID è obbligatorio")
        long organizzatoreID
) {
}
