package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;

public interface ConfirmationRepository {
    void add(Candidate candidate);
    List<Candidate> getConfirmations();
}
