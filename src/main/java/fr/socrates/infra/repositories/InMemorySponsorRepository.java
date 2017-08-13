package fr.socrates.infra.repositories;

import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basti on 12/08/2017.
 */
public class InMemorySponsorRepository implements SponsorRespository {

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
