package fr.socrates.sponsor;

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
        SponsorList emptyListOfSponsors = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
        List<Sponsor> fetchedSponsors = emptyListOfSponsors.getSponsorsList();
        assertThat(fetchedSponsors).isEmpty();
    }

    @Test
    public void should_return_size_1_when_adding_a_sponsor() {
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
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
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
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

    @Test
    public void should_write_in_console() throws Exception {
        Printer consoleWriter = new FakeConsoleWriter();
        SponsorList sponsorList = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
        String name = "name";
        String SIRET = "siret";
        String SIREN = "siren";
        String contractRepresentative="contractRepresentative";
        String contact = "mail1@gmail.com";
        double amountOfSponsoring = 123;

        String nameSponsorTwo = "Sponsor";
        String SIRETSponsorTwo = "siret 2";
        String SIRENSponsorTwo = "siren 2";
        String contractRepresentativeSponsorTwo = "contractRepresentative 2";
        String contactSponsorTwo = "mail2@gmail.com";
        double amountOfSponsoringSponsorTwo = 1234;

        sponsorList.addSponsor(new Sponsor(name,SIRET,SIREN,contractRepresentative,contact,amountOfSponsoring));
        sponsorList.addSponsor(new Sponsor(nameSponsorTwo, SIRETSponsorTwo, SIRENSponsorTwo, contractRepresentativeSponsorTwo, contactSponsorTwo, amountOfSponsoringSponsorTwo));

        assertThat(sponsorList.print()).isEqualTo("mail1@gmail.com,mail2@gmail.com");
    }

    private class FakeConsoleWriter implements Printer {
        public String print(List<Sponsor> sponsorsList) {
            String listOfSponsorsString = "";
            for (int i = 0; i < sponsorsList.size(); i++) {
                listOfSponsorsString += sponsorsList.get(i).getContact();
                if (!(i == sponsorsList.size() - 1))
                    listOfSponsorsString += ",";
            }
            return listOfSponsorsString;
        }
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
