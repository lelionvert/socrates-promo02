package fr.socrates.domain.sponsor;

import java.util.List;

public class SponsorServiceImpl implements SponsorService {
    private final SponsorRespository sponsorRespository;

    public SponsorServiceImpl(SponsorRespository sponsorRespository) {
        this.sponsorRespository = sponsorRespository;
    }

    @Override
    public void addSponsor(Sponsor sponsor) {
        if (!this.sponsorRespository.getSponsorsList().contains(sponsor)) {
            this.sponsorRespository.addSponsor(sponsor);
        }
    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return this.sponsorRespository.getSponsorsList();
    }

}
