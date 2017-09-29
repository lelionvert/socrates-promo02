package fr.socrates.domain.sponsor;

import fr.socrates.domain.inseeCode.Siren;
import fr.socrates.domain.inseeCode.Siret;

public class Sponsor {

    private final String name;
    private final Siren siren;
    private final String contractRepresentative;
    private final String contact;
    private final double amountOfSponsoring;
    private String id;

    private Sponsor(String name, Siren siren, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public Siren getSiren() {
        return siren;
    }

    public String getName() {
        return name;
    }

    public String getContractRepresentative() {
        return contractRepresentative;
    }

    public String getContact() {
        return contact;
    }

    public double getAmountOfSponsoring() {
        return amountOfSponsoring;
    }

    public static class SponsorBuilder {
        private String name;
        private Siren siren;
        private String contractRepresentative;
        private String contact;
        private double amountOfSponsoring;

        public SponsorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SponsorBuilder withSIRET(String siret) {
            if (siret != null) {
                this.withSIREN(new Siret(siret).getSiren());
            }
            return this;
        }

        private SponsorBuilder withSIREN(Siren siren) {
            this.siren = siren;
            return this;
        }

        public SponsorBuilder withSIREN(String siren) {
            this.siren = new Siren(siren);
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
            return new Sponsor(name, siren, contractRepresentative, contact, amountOfSponsoring);
        }
    }
}
