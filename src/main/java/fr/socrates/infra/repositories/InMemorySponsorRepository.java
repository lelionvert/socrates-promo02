package fr.socrates.infra.repositories;

import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySponsorRepository implements SponsorRepository {

    private final List<Sponsor> list = new ArrayList<>();

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        list.add(sponsor);
        return sponsor;
    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return list;
    }
}
