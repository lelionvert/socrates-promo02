package fr.socrates.domain.sponsor;

public class InvalidSIRENException extends InvalidSponsorException {
    public InvalidSIRENException(String siren) {
        super("Invalid SIREN : " + siren);
    }
}
