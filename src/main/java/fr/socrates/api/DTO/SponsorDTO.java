package fr.socrates.api.DTO;

import fr.socrates.domain.sponsor.Sponsor;

public class SponsorDTO {
    private String id;
    private String siren;
    private String siret;
    private String name;
    private String contractRepresentative;
    private String contact;
    private double amount;

    public SponsorDTO() {
    }

    public SponsorDTO(String id, String siren, String siret, String name, String contractRepresentative, String contact, double amount) {
        this.id = id;
        this.siren = siren;
        this.siret = siret;
        this.name = name;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amount = amount;
    }

    public static SponsorDTO domainToDTO(Sponsor sponsor) {
        return new SponsorDTOBuilder()
                .withId(sponsor.getId())
                .withSiren(sponsor.getSiren())
                .withSiret(sponsor.getSiret())
                .withName(sponsor.getName())
                .withContractRepresentative(sponsor.getContractRepresentative())
                .withContact(sponsor.getContact())
                .withAmount(sponsor.getAmountOfSponsoring())
                .createSponsorDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractRepresentative() {
        return contractRepresentative;
    }

    public void setContractRepresentative(String contractRepresentative) {
        this.contractRepresentative = contractRepresentative;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
