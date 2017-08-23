package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;

import java.util.ArrayList;
import java.util.List;

public class InMemoryConfirmationRepository implements ConfirmationRepository {
    private List<Confirmation> listConfirmations;

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
                .filter(confirmation -> candidate.hasCandidateID(confirmation.getCandidateId()))
                .findFirst().isPresent();
    }
}
