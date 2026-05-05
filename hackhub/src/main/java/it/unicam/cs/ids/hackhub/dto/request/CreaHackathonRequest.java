package it.unicam.cs.ids.hackhub.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record CreaHackathonRequest(
        @NotBlank(message = "Il nome è obbligario")
        String nome,
        @NotBlank(message = "Il regolamento è obbligario")
        String regolamento,
        String luogo,
        @NotNull(message = "La data di inizio è obbligatoria")
        Date dataInizio,
        @NotNull(message = "La data di fine è obbligatoria")
        Date dataFine,
        @NotNull(message = "La scadenza delle iscrizioni è obbligatoria")
        Date scadenzaIscrizioni,
        @NotNull(message = "La scadenza delle sottomissioni è obbligatoria")
        Date scadenzaSottomissioni,
        @NotNull(message = "Il premio è obbligatorio")
        float premio,

        @Min(value = 1, message = "La dimensione massima di un team non può essere inferiore a 1")
        int dimensioneMassimoTeam,
        @NotNull(message = "L'ID è obbligatorio")
        long organizzatoreID,
        @NotNull(message = "L'ID è obbligatorio")
        long giudiceID,
        @NotEmpty(message = "Deve essere inserito almeno un Id di un mentore")
        List<Long> mentoriID
) {
}
