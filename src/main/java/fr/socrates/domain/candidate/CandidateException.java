package fr.socrates.domain.candidate;

public class CandidateException extends Exception{
    CandidateException(String message) {
        super(message);
    }

    public static class NotFoundException extends CandidateException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class CandidateExistingException extends CandidateException {
        public CandidateExistingException(String message) {
            super(message);
        }
    }

    public static class CandidatePersisteDataException extends CandidateException {

        public CandidatePersisteDataException(String message) {
            super(message);
        }
    }
}
