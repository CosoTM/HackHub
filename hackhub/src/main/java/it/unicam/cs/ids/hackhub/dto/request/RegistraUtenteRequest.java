package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.Email;

public record RegistraUtenteRequest(
        String nome,
        @Email
        String email,
        String password) {
}
