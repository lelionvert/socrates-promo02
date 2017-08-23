package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRepository;

import java.util.ArrayList;
import java.util.List;


public class InMemorySponsorRepository implements SponsorRepository {

    private final List<Sponsor> list = new ArrayList<>();

    @Override
    public void addSponsor(Sponsor sponsor) {
        list.add(sponsor);
    }

    @Override
    public List<Sponsor> getSponsorsList() {
        return list;
    }
}
