package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

public record InviaSottomissioneRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long membroID
) {
}
