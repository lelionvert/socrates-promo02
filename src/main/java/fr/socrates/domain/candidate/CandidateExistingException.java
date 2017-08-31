package fr.socrates.domain.candidate;

public class CandidateExistingException extends CandidateException {
    public CandidateExistingException(String message) {
        super(message);
    }
}
