package com.miola.smarthotel.helpers;

import java.sql.Time;

public enum EtatReservation
{
    EN_ATTENTE(1,"EN_ATTENTE", "après l’état initial et la création ou l’ajout d’une réservation par le receptioniste."),
    EN_VERIFICATION(2, "EN_VERIFICATION", "l’état de vérification de chambre."),
    RECHERCHE(3, "RECHERCHE", ": la recherche des réservations déjà validées pour chaque client."),
    VALIDEE(4, "VALIDEE", "l’état après la validation de la date de départ prévue."),
    MISE_A_JOUR(5, "MISE_A_JOUR", "mettre a jours les réservations validées."),
    PAYEE(6, "PAYEE", "le payement de réservation par le client."),
    ANNULEE(7, "ANNULEE", "dans l’état en attente avant la réservation par le client."),
    SUPPRIMEE(8, "SUPPRIMEE", "après la validation."),
    RESERVEE(9, "RESERVEE", "l’état final après la vérification et le payement de client.");

    private int id;
    private String etat;
    private String description;

    EtatReservation(int id, String etat, String description) {
        this.id = id;
        this.etat = etat;
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }

    public int getId()
    {
        return id;
    }
}
