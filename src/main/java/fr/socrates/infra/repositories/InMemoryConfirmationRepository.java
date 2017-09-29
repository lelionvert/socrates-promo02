package fr.socrates.infra.repositories;

import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryConfirmationRepository implements ConfirmationRepository {
    private final List<Confirmation> listConfirmations;

    public InMemoryConfirmationRepository() {
        this.listConfirmations = new ArrayList<>();
    }

    @Override
    public void add(Confirmation confirmation) {
        listConfirmations.add(confirmation);

    }

    @Override
    public List<Confirmation> getConfirmations() {
        return listConfirmations;
    }

    @Override
    public boolean confirmationExists(Candidate candidate) {
        return listConfirmations
                .stream()
                .anyMatch(confirmation -> candidate.hasCandidateID(confirmation.getCandidateId()));
    }
}
