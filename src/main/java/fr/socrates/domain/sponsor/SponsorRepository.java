package fr.socrates.domain.sponsor;

import java.util.List;

public interface SponsorRepository {
    Sponsor addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}
