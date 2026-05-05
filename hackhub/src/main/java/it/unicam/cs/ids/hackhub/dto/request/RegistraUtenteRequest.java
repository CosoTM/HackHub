package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistraUtenteRequest(
        @NotBlank(message = "Il nome è obbligatorio")
        String nome,
        @Email(message = "L'email non è nel formato corretto")
        @NotBlank(message = "L'email è obbligatoria")
        String email,
        @NotBlank(message = "La password è obbligatoria")
        String password
) {
}
