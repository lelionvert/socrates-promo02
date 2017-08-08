package fr.socrates.sponsor;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
public class Sponsor {
    private String name;
    private String SIRET;
    private String SIREN;
    private String contractRepresentative;
    private String contact;
    private double amountOfSponsoring;


    public Sponsor(String name, String SIRET, String SIREN, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.name = name;
        this.SIRET = SIRET;
        this.SIREN = SIREN;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amountOfSponsoring = amountOfSponsoring;
    }

    public String getName() {
        return name;
    }

    public String getSIRET() {
        return SIRET;
    }

    public String getSIREN() {
        return SIREN;
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
}
