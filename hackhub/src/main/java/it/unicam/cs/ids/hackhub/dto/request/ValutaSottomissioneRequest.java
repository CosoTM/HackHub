package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record ValutaSottomissioneRequest(
        @NotNull(message = "L'ID è obbligatorio")
        long giudiceID,
        String giudizioScritto,

        @NotNull(message = "Il punteggio è obbligatorio")
        int punteggio
) {
}
