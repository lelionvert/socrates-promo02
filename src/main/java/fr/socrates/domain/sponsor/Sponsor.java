package fr.socrates.domain.sponsor;

public class Sponsor {

    private final String name;
    private final Siret siret;
    private final Siren siren;
    private final String contractRepresentative;
    private final String contact;
    private double amountOfSponsoring;

    private Sponsor(String name, Siret siret, Siren siren, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.name = name;
        this.siret = siret;
        this.siren = siren;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amountOfSponsoring = amountOfSponsoring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        return siren.equals(sponsor.siren);
    }

    @Override
    public int hashCode() {
        return siren.hashCode();
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "contact='" + contact + '\'' +
                '}';
    }

    public static class SponsorBuilder {
        private String name;
        private Siret siret;
        private Siren siren;
        private String contractRepresentative;
        private String contact;
        private double amountOfSponsoring;

        public SponsorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SponsorBuilder withSIRET(String siret) {
            this.siret = Siret.of(siret);
            this.withSIREN(siret.substring(0, 9));
            return this;
        }

        public SponsorBuilder withSIREN(String siren) {
            this.siren = Siren.of(siren);
            return this;
        }

        public SponsorBuilder withContractRepresentative(String contractRepresentative) {
            this.contractRepresentative = contractRepresentative;
            return this;
        }

        public SponsorBuilder withContact(String contact) {
            this.contact = contact;
            return this;
        }

        public SponsorBuilder withAmountOfSponsoring(double amountOfSponsoring) {
            this.amountOfSponsoring = amountOfSponsoring;
            return this;
        }

        public Sponsor createSponsor() {
            return new Sponsor(name, siret, siren, contractRepresentative, contact, amountOfSponsoring);
        }
    }
}
