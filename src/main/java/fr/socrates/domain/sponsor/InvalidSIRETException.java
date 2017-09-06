package fr.socrates.domain.sponsor;

public class InvalidSIRETException extends InvalidSponsorException {
    public InvalidSIRETException(String siret) {
        super("Invalid SIRET : " + siret);
    }
}
