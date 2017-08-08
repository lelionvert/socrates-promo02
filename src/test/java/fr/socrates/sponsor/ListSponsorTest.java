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
        assertThat(0).isEqualTo(fetchedSponsors.size());
    }

    @Test
    public void should_return_size_1_when_adding_a_sponsor() {
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector());
        String name = "name";
        String SIRET = "siret";
        String SIREN = "siren";
        String contractRepresentative="contractRepresentative";
        String contact = "contact";
        double amountOfSponsoring = 123;
        listOfOneSponsor.addSponsor(new Sponsor(name,SIRET,SIREN,contractRepresentative,contact,amountOfSponsoring));
        List<Sponsor> fetchedSponsors = listOfOneSponsor.getSponsorsList();
        assertThat(1).isEqualTo(fetchedSponsors.size());
        assertThat(name).isEqualTo(fetchedSponsors.get(0).getName());
        assertThat(SIRET).isEqualTo(fetchedSponsors.get(0).getSIRET());
        assertThat(SIREN).isEqualTo(fetchedSponsors.get(0).getSIREN());
        assertThat(contractRepresentative).isEqualTo(fetchedSponsors.get(0).getContractRepresentative());
        assertThat(contact).isEqualTo(fetchedSponsors.get(0).getContact());
        assertThat(amountOfSponsoring).isEqualTo(fetchedSponsors.get(0).getAmountOfSponsoring());
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
