package fr.socrates.sponsor;

class Sponsor {
    private final String name;
    private final String SIRET;
    private final String SIREN;
    private final String contractRepresentative;
    private final String contact;
    private final double amountOfSponsoring;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        return SIREN.equals(sponsor.SIREN);
    }

    @Override
    public int hashCode() {
        return SIREN.hashCode();
    }

    Sponsor(String name, String SIRET, String SIREN, String contractRepresentative, String contact, double amountOfSponsoring) {
        this.name = name;
        this.SIRET = SIRET;
        this.SIREN = SIREN;
        this.contractRepresentative = contractRepresentative;
        this.contact = contact;
        this.amountOfSponsoring = amountOfSponsoring;
    }

    void print(Printer printer) {
        printer.print(contact);
    }
}
