package fr.socrates.infra.repositories;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by basti on 12/08/2017.
 */
public class InMemorySponsorRepository implements SponsorRespository{

    @Override
    public void addSponsor(Sponsor sponsor) {

    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return null;
    }
}
