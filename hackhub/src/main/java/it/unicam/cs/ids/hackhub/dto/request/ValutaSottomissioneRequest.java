package it.unicam.cs.ids.hackhub.dto.request;

public record ValutaSottomissioneRequest(
        long giudiceID,
        String giudizioScritto,
        int punteggio
) {
}
