package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EspelliMembroRequest(
        @NotNull(message = "L'ID del capo è obbligatorio")
        long capoID,

        @NotNull(message = "L'ID del espulso è obbligatorio")
        long userEspulsoID) {
}
