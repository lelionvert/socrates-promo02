package fr.socrates.domain.sponsor;

import java.util.List;
import java.util.Optional;

public interface SponsorService {
    Optional<Sponsor> addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}
