package fr.socrates.domain.sponsor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceImpl implements SponsorService {
    private final SponsorRepository sponsorRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public Optional<Sponsor> addSponsor(Sponsor sponsor) {
        if (!this.sponsorRepository.getSponsorsList().contains(sponsor))
            return Optional.of(this.sponsorRepository.addSponsor(sponsor));
        else
            return Optional.empty();
    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return this.sponsorRepository.getSponsorsList();
    }

}
