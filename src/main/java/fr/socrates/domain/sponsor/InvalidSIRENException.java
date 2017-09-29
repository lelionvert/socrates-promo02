package fr.socrates.domain.sponsor;

class InvalidSIRENException extends InvalidSponsorException {
    public InvalidSIRENException(String siren) {
        super("Invalid SIREN : " + siren);
    }
}
