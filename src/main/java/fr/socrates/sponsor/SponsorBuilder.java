package fr.socrates.sponsor;

public class SponsorBuilder {
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
        return new Sponsor(name, siret, siren, contractRepresentative, contact, amountOfSponsoring);
    }
}