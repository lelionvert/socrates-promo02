package fr.socrates.sponsor;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
class Sponsor {
    private final String name;
    private final String SIRET;
    private final String SIREN;
    private final String contractRepresentative;
    private final String contact;
    private final double amountOfSponsoring;


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
