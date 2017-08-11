package fr.socrates.domain.sponsor;

import fr.socrates.common.FakePrinter;
import fr.socrates.common.Printer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListSponsorTest {

    private List<Sponsor> sponsors;

    private void init_list_of_sponsors() {
        Sponsor sponsor1 = new Sponsor.SponsorBuilder()
                .withName("name")
                .withSIRET("siret")
                .withSIREN("siren")
                .withContractRepresentative("contractRepresentative")
                .withContact("mail1@gmail.com")
                .withAmountOfSponsoring(123d).createSponsor();

        Sponsor sponsor2 = new Sponsor.SponsorBuilder()
                .withName("name2")
                .withSIRET("siret2")
                .withSIREN("siren2")
                .withContractRepresentative("contractRepresentative2")
                .withContact("mail2@gmail.com")
                .withAmountOfSponsoring(1234d).createSponsor();

        sponsors = new ArrayList<>();
        sponsors.add(sponsor1);
        sponsors.add(sponsor2);
    }

    @Test
    public void should_return_empty_list_sponsors_zero() {
        SponsorService emptyListOfSponsors = new SponsorService(new FakeSponsorRespository(), new FakePrinter());
        List<Sponsor> sponsors = emptyListOfSponsors.getSponsorsList();
        assertThat(sponsors).isEmpty();
    }

    @Test
    public void should_return_size_1_when_adding_a_sponsor() {
        SponsorService listOfOneSponsor = new SponsorService(new FakeSponsorRespository(), new FakePrinter());
        listOfOneSponsor.addSponsor(new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123).createSponsor());
        List<Sponsor> sponsors = listOfOneSponsor.getSponsorsList();


        Sponsor sponsorExpected = new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();
        Sponsor sponsor = sponsors.get(0);
        assertThat(sponsors.size()).isEqualTo(1);
        assertThat(sponsor).isEqualTo(sponsorExpected);
    }

    @Test
    public void should_return_one_sponsor_when_adding_two_identical_sponsors() throws Exception {
        SponsorService listOfTwoSponsors = new SponsorService(new FakeSponsorRespository(), new FakePrinter());
        Sponsor sponsor1 = new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor();
        Sponsor sponsor2 = new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();

        listOfTwoSponsors.addSponsor(sponsor1);
        listOfTwoSponsors.addSponsor(sponsor2);
        List<Sponsor> sponsors = listOfTwoSponsors.getSponsorsList();

        assertThat(sponsors.size()).isEqualTo(1);
        assertThat(sponsors).contains(sponsor1);
    }

    @Test
    public void should_return_two_sponsors_when_adding_two_sponsors() {
        SponsorService listOfOneSponsor = new SponsorService(new FakeSponsorRespository(), new FakePrinter());

        listOfOneSponsor.addSponsor(new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor());
        listOfOneSponsor.addSponsor(new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("siret 2").withSIREN("siren 2").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor());
        List<Sponsor> sponsors = listOfOneSponsor.getSponsorsList();

        Sponsor sponsor1Expected = new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();
        Sponsor sponsor = sponsors.get(0);
        assertThat(sponsors.size()).isEqualTo(2);
        assertThat(sponsor).isEqualTo(sponsor1Expected);

        Sponsor sponsor2Expected = new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("siret 2").withSIREN("siren 2").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor();
        Sponsor sponsor1 = sponsors.get(1);
        assertThat(sponsor1).isEqualTo(sponsor2Expected);
    }

    @Test
    public void should_write_in_console() throws Exception {
        init_list_of_sponsors();
        FakePrinter printer = new FakePrinter();
        SponsorService sponsorService = new SponsorService(new FakeSponsorRespository(), printer);

        sponsorService.addSponsor(new Sponsor.SponsorBuilder().withName("name").withSIRET("siret").withSIREN("siren").withContractRepresentative("contractRepresentative").withContact("mail1@gmail.com").withAmountOfSponsoring(123d).createSponsor());
        sponsorService.addSponsor(new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("siret 2").withSIREN("siren 2").withContractRepresentative("contractRepresentative 2").withContact("mail2@gmail.com").withAmountOfSponsoring(1234d).createSponsor());

        sponsorService.print();

        assertThat(printer.flush()).isEqualTo("mail1@gmail.com; mail2@gmail.com");
    }

    @Test
    public void should_call_sponsor_connector() {
        init_list_of_sponsors();
        FakeSponsorRespository sponsorConnector = new FakeSponsorRespository(sponsors);
        SponsorService listOfTwoSponsors = new SponsorService(sponsorConnector, new FakePrinter());
        assertThat(sponsorConnector.getSponsorsList()).isEqualTo(sponsors);
        assertThat(listOfTwoSponsors.getSponsorsList().size()).isEqualTo(2);
    }



    private class FakeSponsorRespository implements SponsorRespository {
        private final List<Sponsor> sponsors;

        public FakeSponsorRespository() {
            sponsors = new ArrayList<>();
        }

        public FakeSponsorRespository(List<Sponsor> sponsors) {

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
