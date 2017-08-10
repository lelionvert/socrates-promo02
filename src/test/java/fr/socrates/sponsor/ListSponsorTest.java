package fr.socrates.sponsor;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ListSponsorTest {

    private List<Sponsor> sponsors;

    private void init_list_of_sponsors() {
        Sponsor sponsor1 = new SponsorBuilder()
            .setName("name")
            .setSIRET("siret")
            .setSIREN("siren")
            .setContractRepresentative("contractRepresentative")
            .setContact("mail1@gmail.com")
            .setAmountOfSponsoring((double) 123).createSponsor();

        Sponsor sponsor2 = new SponsorBuilder()
            .setName("name2")
            .setSIRET("siret2")
            .setSIREN("siren2")
            .setContractRepresentative("contractRepresentative2")
            .setContact("mail2@gmail.com")
            .setAmountOfSponsoring((double) 1234).createSponsor();

        sponsors = new ArrayList<Sponsor>();
        sponsors.add(sponsor1);
        sponsors.add(sponsor2);
    }

    @Test
    public void should_return_empty_list_sponsors_zero(){
        SponsorList emptyListOfSponsors = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
        List<Sponsor> fetchedSponsors = emptyListOfSponsors.getSponsorsList();
        assertThat(fetchedSponsors).isEmpty();
    }

    @Test
    public void should_return_size_1_when_adding_a_sponsor() {
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());
        listOfOneSponsor.addSponsor(new SponsorBuilder().setName("name").setSIRET("siret").setSIREN("siren").setContractRepresentative("contractRepresentative").setContact("contact").setAmountOfSponsoring((double) 123).createSponsor());
        List<Sponsor> fetchedSponsors = listOfOneSponsor.getSponsorsList();

        Sponsor sponsor = fetchedSponsors.get(0);
        assertThat(fetchedSponsors.size()).isEqualTo(1);
        assertThat(sponsor.getName()).isEqualTo("name");
        assertThat(sponsor.getSIRET()).isEqualTo("siret");
        assertThat(sponsor.getSIREN()).isEqualTo("siren");
        assertThat(sponsor.getContractRepresentative()).isEqualTo("contractRepresentative");
        assertThat(sponsor.getContact()).isEqualTo("contact");
        assertThat(sponsor.getAmountOfSponsoring()).isEqualTo((double) 123);
    }

    @Test
    public void should_return_two_sponsors_when_adding_a_sponsor() {
        SponsorList listOfOneSponsor = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());

        listOfOneSponsor.addSponsor(new SponsorBuilder().setName("name").setSIRET("siret").setSIREN("siren").setContractRepresentative("contractRepresentative").setContact("contact").setAmountOfSponsoring((double) 123).createSponsor());
        listOfOneSponsor.addSponsor(new SponsorBuilder().setName("Sponsor").setSIRET("siret 2").setSIREN("siren 2").setContractRepresentative("contractRepresentative 2").setContact("contact 2").setAmountOfSponsoring((double) 1234).createSponsor());
        List<Sponsor> fetchedSponsors = listOfOneSponsor.getSponsorsList();

        Sponsor sponsor = fetchedSponsors.get(0);
        assertThat(fetchedSponsors.size()).isEqualTo(2);
        assertThat(sponsor.getName()).isEqualTo("name");
        assertThat(sponsor.getSIRET()).isEqualTo("siret");
        assertThat(sponsor.getSIREN()).isEqualTo("siren");
        assertThat(sponsor.getContractRepresentative()).isEqualTo("contractRepresentative");
        assertThat(sponsor.getContact()).isEqualTo("contact");
        assertThat(sponsor.getAmountOfSponsoring()).isEqualTo((double) 123);

        Sponsor sponsor1 = fetchedSponsors.get(1);
        assertThat(sponsor1.getName()).isEqualTo("Sponsor");
        assertThat(sponsor1.getSIRET()).isEqualTo("siret 2");
        assertThat(sponsor1.getSIREN()).isEqualTo("siren 2");
        assertThat(sponsor1.getContractRepresentative()).isEqualTo("contractRepresentative 2");
        assertThat(sponsor1.getContact()).isEqualTo("contact 2");
        assertThat(sponsor1.getAmountOfSponsoring()).isEqualTo((double) 1234);
    }

    @Test
    public void should_write_in_console() throws Exception {
        SponsorList sponsorList = new SponsorList(new FakeSponsorConnector(), new FakeConsoleWriter());

        sponsorList.addSponsor(new SponsorBuilder().setName("name").setSIRET("siret").setSIREN("siren").setContractRepresentative("contractRepresentative").setContact("mail1@gmail.com").setAmountOfSponsoring((double) 123).createSponsor());
        sponsorList.addSponsor(new SponsorBuilder().setName("Sponsor").setSIRET("siret 2").setSIREN("siren 2").setContractRepresentative("contractRepresentative 2").setContact("mail2@gmail.com").setAmountOfSponsoring((double) 1234).createSponsor());

        assertThat(sponsorList.print()).isEqualTo("mail1@gmail.com,mail2@gmail.com");
    }

    @Test
    public void should_call_sponsor_connector(){
        init_list_of_sponsors();
        FakeSponsorConnector sponsorConnector = new FakeSponsorConnector(sponsors);
        SponsorList listOfTwoSponsors = new SponsorList(sponsorConnector, new FakeConsoleWriter());
        assertThat(sponsorConnector.getSponsorsList()).isEqualTo(sponsors);
        assertThat(listOfTwoSponsors.getSponsorsList().size()).isEqualTo(2);
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
        private List<Sponsor> sponsors;

        public FakeSponsorConnector() {
            sponsors = new ArrayList<Sponsor>();
        }

        public FakeSponsorConnector(List<Sponsor> sponsors) {

            this.sponsors = sponsors;
        }

        public void addSponsor(Sponsor sponsor) {
            sponsors.add(sponsor);
        }

        public List<Sponsor> getSponsorsList() {
            return sponsors;
        }


    }
}
