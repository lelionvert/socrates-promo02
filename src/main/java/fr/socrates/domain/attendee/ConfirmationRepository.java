package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;

public interface ConfirmationRepository {

    void add(Confirmation confirmation);

    List<Confirmation> getConfirmations();

    boolean confirmationExists(Candidate candidate);
}
