package fr.socrates.domain.candidate.exceptions;

public class CandidateException extends Exception {
    public static class NotFoundException extends CandidateException {

    }

    public static class CandidateExistingException extends CandidateException {

    }

    public static class CandidatePersisteDataException extends CandidateException {

    }
}
