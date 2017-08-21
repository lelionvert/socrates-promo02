package fr.socrates.domain.sponsor;

import java.util.List;

public interface SponsorRepository {
    void addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}
