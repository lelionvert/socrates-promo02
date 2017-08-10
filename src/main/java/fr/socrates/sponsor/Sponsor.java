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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        if (Double.compare(sponsor.amountOfSponsoring, amountOfSponsoring) != 0) return false;
        if (name != null ? !name.equals(sponsor.name) : sponsor.name != null) return false;
        if (SIRET != null ? !SIRET.equals(sponsor.SIRET) : sponsor.SIRET != null) return false;
        if (SIREN != null ? !SIREN.equals(sponsor.SIREN) : sponsor.SIREN != null) return false;
        if (contractRepresentative != null ? !contractRepresentative.equals(sponsor.contractRepresentative) : sponsor.contractRepresentative != null)
            return false;
        return contact != null ? contact.equals(sponsor.contact) : sponsor.contact == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (SIRET != null ? SIRET.hashCode() : 0);
        result = 31 * result + (SIREN != null ? SIREN.hashCode() : 0);
        result = 31 * result + (contractRepresentative != null ? contractRepresentative.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        temp = Double.doubleToLongBits(amountOfSponsoring);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    void print(Printer printer) {
        printer.print(contact);
    }
}
