package fr.socrates.sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
class SponsorList {
    private final Printer printer;
    private final SponsorConnector sponsorConnector;

    public SponsorList(SponsorConnector sponsorConnector, Printer printer) {
        this.printer = printer;
        this.sponsorConnector = sponsorConnector;
    }

    public void addSponsor(Sponsor sponsor) {
        this.sponsorConnector.addSponsor(sponsor);
    }

    public List<Sponsor> getSponsorsList() {
        return this.sponsorConnector.getSponsorsList();
    }

    public void print() {
      for (Sponsor sponsor : getSponsorsList()) {
        sponsor.print(printer);
      }
    }
}
