package it.unicam.cs.ids.hackhub.dto.request;

public record SegnalaTeamRequest(
        long mentoreID,
        long teamID,
        long hackathonID,
        String motivoSegnalazione
) {
}
