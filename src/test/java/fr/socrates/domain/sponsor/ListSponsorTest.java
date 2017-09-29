package fr.socrates.domain.sponsor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListSponsorTest {

    private List<Sponsor> sponsors;

    private void init_list_of_sponsors() {
        Sponsor sponsor1 = new Sponsor.SponsorBuilder()
                .withName("name")
                .withSIRET("82322757400014")
                .withSIREN("823227574")
                .withContractRepresentative("contractRepresentative")
                .withContact("mail1@gmail.com")
                .withAmountOfSponsoring(123d).createSponsor();

        Sponsor sponsor2 = new Sponsor.SponsorBuilder()
                .withName("name2")
                .withSIRET("51922005700011")
                .withSIREN("519220057")
                .withContractRepresentative("contractRepresentative2")
                .withContact("mail2@gmail.com")
                .withAmountOfSponsoring(1234d).createSponsor();

        sponsors = new ArrayList<>();
        sponsors.add(sponsor1);
        sponsors.add(sponsor2);
    }

    @Test
    public void should_return_empty_list_sponsors_zero() {
        SponsorService emptyListOfSponsors = new SponsorServiceImpl(new FakeSponsorRepository());
        List<Sponsor> sponsors = emptyListOfSponsors.getSponsorsList();
        assertThat(sponsors).isEmpty();
    }

    @Test
    public void should_list_only_one_sponsor_when_one_sponsor_is_added() throws InvalidSponsorException {
        SponsorService sponsorService = new SponsorServiceImpl(new FakeSponsorRepository());

        sponsorService.addSponsor(new Sponsor.SponsorBuilder().withName("name").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123).createSponsor());
        List<Sponsor> sponsors = sponsorService.getSponsorsList();

        Sponsor sponsorExpected = new Sponsor.SponsorBuilder().withName("name").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();
        assertThat(sponsors).containsExactly(sponsorExpected);
    }

    @Test
    public void should_list_only_one_sponsor_when_the_same_sponsor_is_added_twice() throws Exception {
        SponsorService sponsorService = new SponsorServiceImpl(new FakeSponsorRepository());
        Sponsor sponsor1 = new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor();
        Sponsor sponsor2 = new Sponsor.SponsorBuilder().withName("name").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();

        sponsorService.addSponsor(sponsor1);
        sponsorService.addSponsor(sponsor2);

        List<Sponsor> sponsors = sponsorService.getSponsorsList();
        assertThat(sponsors).containsExactly(sponsor1);
    }

    @Test
    public void should_return_two_sponsors_when_adding_two_sponsors() throws InvalidSponsorException {
        SponsorService listOfOneSponsor = new SponsorServiceImpl(new FakeSponsorRepository());

        listOfOneSponsor.addSponsor(new Sponsor.SponsorBuilder().withName("name").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor());
        listOfOneSponsor.addSponsor(new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("51922005700011").withSIREN("519220057").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor());
        List<Sponsor> sponsors = listOfOneSponsor.getSponsorsList();

        Sponsor sponsor1Expected = new Sponsor.SponsorBuilder().withName("name").withSIRET("82322757400014").withSIREN("823227574").withContractRepresentative("contractRepresentative").withContact("contact").withAmountOfSponsoring(123d).createSponsor();
        Sponsor sponsor = sponsors.get(0);
        assertThat(sponsors.size()).isEqualTo(2);
        assertThat(sponsor).isEqualTo(sponsor1Expected);

        Sponsor sponsor2Expected = new Sponsor.SponsorBuilder().withName("Sponsor").withSIRET("51922005700011").withSIREN("519220057").withContractRepresentative("contractRepresentative 2").withContact("contact 2").withAmountOfSponsoring(1234d).createSponsor();
        Sponsor sponsor1 = sponsors.get(1);
        assertThat(sponsor1).isEqualTo(sponsor2Expected);
    }


    @Test
    public void should_call_sponsor_connector() throws InvalidSponsorException {
        init_list_of_sponsors();
        FakeSponsorRepository sponsorConnector = new FakeSponsorRepository(sponsors);
        SponsorService listOfTwoSponsors = new SponsorServiceImpl(sponsorConnector);
        assertThat(sponsorConnector.getSponsorsList()).isEqualTo(sponsors);
        assertThat(listOfTwoSponsors.getSponsorsList().size()).isEqualTo(2);
    }



    private class FakeSponsorRepository implements SponsorRepository {
        private final List<Sponsor> sponsors;

        FakeSponsorRepository() {
            sponsors = new ArrayList<>();
        }

        FakeSponsorRepository(List<Sponsor> sponsors) {

            this.sponsors = sponsors;
        }

        public Sponsor addSponsor(Sponsor sponsor) {
            sponsors.add(sponsor);
            return sponsor;
        }

        public List<Sponsor> getSponsorsList() {
            return sponsors;
        }
    }
}
