package fr.socrates.domain.sponsor;

public class Sponsor {

    private final SponsorID sponsorID;
    private String name;
    private Siret siret;
    private final String siren;
    private String contractRepresentative;
    private String contact;
    private double amountOfSponsoring;

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

    private Sponsor(SponsorID sponsorID, String name, Siret siret, String siren, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.sponsorID = sponsorID;
        this.name = name;
        this.siret = siret;
        this.siren = siren;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amountOfSponsoring = amountOfSponsoring;
    }

    public static class SponsorBuilder {
        private String name;
        private Siret siret;
        private String siren;
        private String contractRepresentative;
        private String contact;
        private double amountOfSponsoring;

        public SponsorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SponsorBuilder withSIRET(String siret) {
            this.siret = Siret.of(siret);
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
            if ((siren == null || !this.isSirenValid(siren))) {
                throw new IllegalStateException("Siren must be valid and not empty  ");
            }
            return new Sponsor(new SponsorID(siren), name, siret, siren, contractRepresentative, contact, amountOfSponsoring);
        }

        private boolean isSirenValid(String siren) {
            int total = 0;
            int digit = 0;

            for (int i = 0; i < siren.length(); i++) {
                if ((i % 2) == 1) {
                    digit = Integer.parseInt(String.valueOf(siren.charAt(i))) * 2;
                    if (digit > 9) digit -= 9;
                } else digit = Integer.parseInt(String.valueOf(siren.charAt(i)));
                total += digit;
            }
            return (total % 10) == 0;
        }
    }
}
