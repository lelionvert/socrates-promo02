package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;
import java.util.Optional;

public interface ConfirmationRepository {
    void add(Candidate candidate);
    List<Candidate> getConfirmations();

    Optional<Candidate> findConfirmation(Candidate candidate);
}
