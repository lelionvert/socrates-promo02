package fr.socrates.domain.sponsor;

public class Siret {
    private final String siret;

    public Siret(String siret) {
        this.siret = siret;
    }

    public static Siret of(String siret) {
        SiretValidator siretValidator = new SiretValidator();
        if (!siretValidator.isSiretSyntaxValid(siret)) {
            throw new IllegalStateException("Siret is invalid : " + siret);
        }
        return new Siret(siret);
    }
}
