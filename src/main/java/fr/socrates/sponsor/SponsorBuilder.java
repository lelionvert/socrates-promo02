package fr.socrates.sponsor;

public class SponsorBuilder {
    private String name;
    private String siret;
    private String siren;
    private String contractRepresentative;
    private String contact;
    private double amountOfSponsoring;

    public SponsorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SponsorBuilder setSIRET(String siret) {
        this.siret = siret;
        return this;
    }

    public SponsorBuilder setSIREN(String siren) {
        this.siren = siren;
        return this;
    }

    public SponsorBuilder setContractRepresentative(String contractRepresentative) {
        this.contractRepresentative = contractRepresentative;
        return this;
    }

    public SponsorBuilder setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public SponsorBuilder setAmountOfSponsoring(double amountOfSponsoring) {
        this.amountOfSponsoring = amountOfSponsoring;
        return this;
    }

    public Sponsor createSponsor() {
        return new Sponsor(name, siret, siren, contractRepresentative, contact, amountOfSponsoring);
    }
}