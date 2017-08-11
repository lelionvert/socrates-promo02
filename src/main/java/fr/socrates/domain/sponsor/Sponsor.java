package fr.socrates.domain.sponsor;

import fr.socrates.common.Printer;

public class Sponsor {
    private final String name;
    private final String SIRET;
    private final String SIREN;
    private final String contractRepresentative;
    private final String contact;
    private final double amountOfSponsoring;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        return SIREN.equals(sponsor.SIREN);
    }

    @Override
    public int hashCode() {
        return SIREN.hashCode();
    }

    private Sponsor(String name, String SIRET, String SIREN, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.name = name;
        this.SIRET = SIRET;
        this.SIREN = SIREN;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amountOfSponsoring = amountOfSponsoring;
    }

    void print(Printer printer) {
        printer.print(contact);
    }

    public static class SponsorBuilder {
        private String name;
        private String siret;
        private String siren;
        private String contractRepresentative;
        private String contact;
        private double amountOfSponsoring;

        public SponsorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SponsorBuilder withSIRET(String siret) {
            this.siret = siret;
            return this;
        }

        public SponsorBuilder withSIREN(String siren) {
            this.siren = siren;
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
            if(siren==null)
                throw new IllegalStateException();
            return new Sponsor(name, siret, siren, contractRepresentative, contact, amountOfSponsoring);
        }
    }
}
