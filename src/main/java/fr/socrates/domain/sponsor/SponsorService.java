package fr.socrates.domain.sponsor;

import java.util.List;

public interface SponsorService {
    void addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}
