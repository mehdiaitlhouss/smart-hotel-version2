package com.miola.smarthotel.helpers;

public enum EtatReservation
{
    EN_ATTENTE("en attente", "après l’état initial et la création ou l’ajout d’une réservation par le receptioniste."),
    EN_VERIFICATION("En vérification", "l’état de vérification de chambre."),
    RECHERCHE("Recherché", ": la recherche des réservations déjà validées pour chaque client."),
    VALIDEE("Validée", "l’état après la validation de la date de départ prévue."),
    MISE_A_JOUR("Mise a jour", "mettre a jours les réservations validées."),
    PAYEE("Payée", "le payement de réservation par le client."),
    ANNULEE("Annulée", "dans l’état en attente avant la réservation par le client."),
    SUPPRIMEE("Supprimée", "après la validation."),
    RESERVEE("Réservée", "l’état final après la vérification et le payement de client.");

    private String etat;
    private String description;

    EtatReservation(String etat, String description) {
        this.etat = etat;
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }
}
