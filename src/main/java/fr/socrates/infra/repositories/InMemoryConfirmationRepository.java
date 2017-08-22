package fr.socrates.infra.repositories;

import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryConfirmationRepository implements ConfirmationRepository {
    private List<Candidate> listAttendees;
    private List<Confirmation> listConfirmations;

    public InMemoryConfirmationRepository() {
        this.listAttendees = new ArrayList<>();
        this.listConfirmations = new ArrayList<>();
    }

    @Override
    public void add(Candidate candidate) {
        listAttendees.add(candidate);
    }

    @Override
    public void add(Confirmation confirmation) {
        listConfirmations.add(confirmation);

    }

    @Override
    public List<Candidate> getConfirmations() {
        return listAttendees;
    }

    @Override
    public List<Confirmation> getRealConfirmations() {
        return listConfirmations;
    }

    @Override
    public Optional<Candidate> findConfirmation(Candidate candidate) {
        return listAttendees
                .stream()
                .filter(candidate::equals)
                .findAny();
    }
}
