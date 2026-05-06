package it.unicam.cs.ids.hackhub.dto.request;

import it.unicam.cs.ids.hackhub.model.StatoHackathon;

public record CambiaStatoHackathonRequest(
        Long organizzatoreID,
        StatoHackathon stato
) {
}
