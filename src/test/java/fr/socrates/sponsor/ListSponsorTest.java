package fr.socrates.sponsor;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
public class ListSponsorTest {

    @Test
    public void should_return_empty_list_sponsors_zero(){
        SponsorList emptyListOfSponsors = new SponsorList(new FakeSponsorConnector());
        List<Sponsor> fetchedSponsors = emptyListOfSponsors.getSponsorsList();
        Assert.assertEquals(0, fetchedSponsors.size());
    }

    private class FakeSponsorConnector implements SponsorConnector {
        List<Sponsor> sponsorsList;

        public FakeSponsorConnector() {
            this.sponsorsList = new ArrayList<Sponsor>();
        }

        public void addSponsor(Sponsor sponsor) {
            this.sponsorsList.add(sponsor);
        }

        public List<Sponsor> getSponsorsList() { return this.sponsorsList; }
    }
}
