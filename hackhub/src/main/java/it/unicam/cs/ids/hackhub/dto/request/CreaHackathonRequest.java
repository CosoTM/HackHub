package it.unicam.cs.ids.hackhub.dto.request;

import java.util.Date;
import java.util.List;

public record CreaHackathonRequest(
        String nome,
        String regolamento,
        String luogo,
        Date dataInizio,
        Date dataFine,
        Date scadenzaSottomissioni,
        float premio,
        int dimensioneMassimoTeam,
        long organizzatoreID,
        long giudiceID,
        List<Long> mentoriID
) {
}
