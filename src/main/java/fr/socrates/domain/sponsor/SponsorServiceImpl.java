package fr.socrates.domain.sponsor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {
    private final SponsorRepository sponsorRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public void addSponsor(Sponsor sponsor) {
        if (!this.sponsorRepository.getSponsorsList().contains(sponsor)) {
            this.sponsorRepository.addSponsor(sponsor);
        }
    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return this.sponsorRepository.getSponsorsList();
    }

}
