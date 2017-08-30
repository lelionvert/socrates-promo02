package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.exceptions.CandidateException;

import java.util.List;

public interface ConfirmationService {
    List<Candidate> getListAttendee() throws CandidateException;
    boolean confirm(String candidateEmail) throws CandidateException;
}
