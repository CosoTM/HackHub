package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RispondiRichiestaRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long mentoreID,
        @NotBlank(message = "la risposta è obbligatoria")
        String risposta
) {
}
