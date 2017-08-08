package fr.socrates.sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
public class SponsorList {
    List<Sponsor> sponsorsList;
    private SponsorConnector sponsorConnector;

    public SponsorList(SponsorConnector sponsorConnector) {
        this.sponsorConnector = sponsorConnector;
        this.sponsorsList = new ArrayList<Sponsor>();
    }

    public void addSponsor(Sponsor sponsor) {
        this.sponsorConnector.addSponsor(sponsor);
    }


    public List<Sponsor> getSponsorsList() {
        return this.sponsorConnector.getSponsorsList();
    }
}
