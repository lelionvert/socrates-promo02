package fr.socrates.domain.candidate;

class Candidate {
    private final String email;

    Candidate(String email) {
        this.email = email;
    }

    String getEmail() {
        return email;
    }
}
