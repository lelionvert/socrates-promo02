package fr.socrates.sponsor;

import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
public interface SponsorConnector {
    void addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}