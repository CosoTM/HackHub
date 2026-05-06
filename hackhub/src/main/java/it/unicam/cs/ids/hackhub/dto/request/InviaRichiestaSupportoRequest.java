package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviaRichiestaSupportoRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long teamID,
        @NotNull(message = "L'ID è obbligatorio")
        Long hackathonID,
        @NotBlank(message = "Il titolo della richiesta è obbligatoria")
        String titolo,
        @NotBlank(message = "Il corpo della richiesta è obbligatoria")
        String corpo
) {
}
