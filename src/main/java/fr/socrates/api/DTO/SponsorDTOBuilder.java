package fr.socrates.api.DTO;

public class SponsorDTOBuilder {
    private String id;
    private String siren;
    private String siret;
    private String name;
    private String contractRepresentative;
    private String contact;
    private double amount;

    public SponsorDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public SponsorDTOBuilder withSiren(String siren) {
        this.siren = siren;
        return this;
    }

    public SponsorDTOBuilder withSiret(String siret) {
        this.siret = siret;
        return this;
    }

    public SponsorDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SponsorDTOBuilder withContractRepresentative(String contractRepresentative) {
        this.contractRepresentative = contractRepresentative;
        return this;
    }

    public SponsorDTOBuilder withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public SponsorDTOBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public SponsorDTO createSponsorDTO() {
        return new SponsorDTO(id, siret, siren, name, contractRepresentative, contact, amount);
    }
}