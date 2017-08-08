package fr.socrates.sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
class SponsorList {
    private final Printer printer;
    private final List<Sponsor> sponsorsList;
    private final SponsorConnector sponsorConnector;

    public SponsorList(SponsorConnector sponsorConnector, Printer printer) {
        this.printer = printer;
        this.sponsorConnector = sponsorConnector;
        this.sponsorsList = new ArrayList<Sponsor>();
    }

    public void addSponsor(Sponsor sponsor) {
        this.sponsorConnector.addSponsor(sponsor);
    }

    public List<Sponsor> getSponsorsList() {
        return this.sponsorConnector.getSponsorsList();
    }

    public String print() {
        return this.printer.print(getSponsorsList());
    }
}
