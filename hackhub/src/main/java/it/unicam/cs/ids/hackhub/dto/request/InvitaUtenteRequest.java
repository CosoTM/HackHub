package it.unicam.cs.ids.hackhub.dto.request;

public record InvitaUtenteRequest(
        long userInvitatoID,
        long capoID,
        long teamID
) {
}
