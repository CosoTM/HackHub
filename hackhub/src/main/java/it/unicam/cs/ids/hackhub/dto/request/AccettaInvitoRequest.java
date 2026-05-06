package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccettaInvitoRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long userID
) {
}
