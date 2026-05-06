package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbbandonaTeamRequest(
        @NotNull(message = "L'ID è obbligatorio")
        Long membroID
) {
}
