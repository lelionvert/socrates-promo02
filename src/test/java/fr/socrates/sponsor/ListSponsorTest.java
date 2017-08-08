package fr.socrates.sponsor;

import org.junit.Assert;
import org.junit.Test;

import javax.net.ssl.SNIServerName;

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
        assertThat(fetchedSponsors).isEmpty();
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
        Sponsor sponsor = fetchedSponsors.get(0);
        assertThat(fetchedSponsors.size()).isEqualTo(1);
        assertThat(sponsor.getName()).isEqualTo(name);
        assertThat(sponsor.getSIRET()).isEqualTo(SIRET);
        assertThat(sponsor.getSIREN()).isEqualTo(SIREN);
        assertThat(sponsor.getContractRepresentative()).isEqualTo(contractRepresentative);
        assertThat(sponsor.getContact()).isEqualTo(contact);
        assertThat(sponsor.getAmountOfSponsoring()).isEqualTo(amountOfSponsoring);
    }

    @Test
    public void should_return_two_sponsors_when_adding_a_sponsor() {
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector());
        String name = "name";
        String SIRET = "siret";
        String SIREN = "siren";
        String contractRepresentative="contractRepresentative";
        String contact = "contact";
        double amountOfSponsoring = 123;

        String nameSponsorTwo = "Sponsor";
        String SIRETSponsorTwo = "siret 2";
        String SIRENSponsorTwo = "siren 2";
        String contractRepresentativeSponsorTwo = "contractRepresentative 2";
        String contactSponsorTwo = "contact 2";
        double amountOfSponsoringSponsorTwo = 1234;

        listOfOneSponsor.addSponsor(new Sponsor(name,SIRET,SIREN,contractRepresentative,contact,amountOfSponsoring));
        listOfOneSponsor.addSponsor(new Sponsor(nameSponsorTwo, SIRETSponsorTwo, SIRENSponsorTwo, contractRepresentativeSponsorTwo, contactSponsorTwo, amountOfSponsoringSponsorTwo));
        List<Sponsor> fetchedSponsors = listOfOneSponsor.getSponsorsList();
        Sponsor sponsor = fetchedSponsors.get(0);
        assertThat(fetchedSponsors.size()).isEqualTo(2);
        assertThat(sponsor.getName()).isEqualTo(name);
        assertThat(sponsor.getSIRET()).isEqualTo(SIRET);
        assertThat(sponsor.getSIREN()).isEqualTo(SIREN);
        assertThat(sponsor.getContractRepresentative()).isEqualTo(contractRepresentative);
        assertThat(sponsor.getContact()).isEqualTo(contact);
        assertThat(sponsor.getAmountOfSponsoring()).isEqualTo(amountOfSponsoring);

        Sponsor sponsor1 = fetchedSponsors.get(1);
        assertThat(sponsor1.getName()).isEqualTo(nameSponsorTwo);
        assertThat(sponsor1.getSIRET()).isEqualTo(SIRETSponsorTwo);
        assertThat(sponsor1.getSIREN()).isEqualTo(SIRENSponsorTwo);
        assertThat(sponsor1.getContractRepresentative()).isEqualTo(contractRepresentativeSponsorTwo);
        assertThat(sponsor1.getContact()).isEqualTo(contactSponsorTwo);
        assertThat(sponsor1.getAmountOfSponsoring()).isEqualTo(amountOfSponsoringSponsorTwo);
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
