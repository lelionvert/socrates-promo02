package fr.socrates.domain.sponsor;

class InvalidSIRETException extends InvalidSponsorException {
    public InvalidSIRETException(String siret) {
        super("Invalid SIRET : " + siret);
    }
}
