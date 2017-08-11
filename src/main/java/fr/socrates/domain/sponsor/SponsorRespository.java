package fr.socrates.domain.sponsor;

import java.util.List;

interface SponsorRespository {
    void addSponsor(Sponsor sponsor);
    List<Sponsor> getSponsorsList();
}
