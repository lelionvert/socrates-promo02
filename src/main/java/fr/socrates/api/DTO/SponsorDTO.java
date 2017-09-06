package fr.socrates.api.DTO;

import fr.socrates.domain.sponsor.Sponsor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SponsorDTO {
    private String id;
    @NotNull
    @Size(min = 9, max = 11)
    private String siren;
    @Size(min = 14, max = 14)
    private String siret;
    @NotNull
    private String name;
    @NotNull
    private String contractRepresentative;
    @NotNull
    private String contact;
    @NotNull
    private double amount;

    public SponsorDTO() {
    }

    public SponsorDTO(String id, String siret, String siren, String name, String contractRepresentative, String contact, double amount) {
        this.id = id;
        this.siret = siret;
        this.siren = siren;
        this.name = name;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amount = amount;
    }

    public static SponsorDTO domainToDTO(Sponsor sponsor) {
        return new SponsorDTOBuilder()
                .withId(sponsor.getId())
                .withSiren(sponsor.getSiren().getSiren())
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

    public static Sponsor dTOtoDomain(SponsorDTO sponsorDTO) {
        return new Sponsor.SponsorBuilder()
                .withSIRET(sponsorDTO.siret)
                .withSIREN(sponsorDTO.siren)
                .withName(sponsorDTO.name)
                .withContractRepresentative(sponsorDTO.contractRepresentative)
                .withContact(sponsorDTO.contact)
                .withAmountOfSponsoring(sponsorDTO.amount)
                .createSponsor();
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }
}
