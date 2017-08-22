package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;
import java.util.Optional;

public interface ConfirmationRepository {
    @Deprecated
    void add(Candidate candidate);

    void add(Confirmation confirmation);

    @Deprecated
    List<Candidate> getConfirmations();

    List<Confirmation> getRealConfirmations();

    Optional<Candidate> findConfirmation(Candidate candidate);
}
