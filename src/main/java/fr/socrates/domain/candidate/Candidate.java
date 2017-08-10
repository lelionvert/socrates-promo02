package fr.socrates.domain.candidate;

class Candidate {
    private final String email;
    private EMail emailToKeep;

    Candidate(String email) {
        this.email = email;
    }

    public Candidate(EMail email) {
        emailToKeep = email;
        this.email = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return email != null ? email.equals(candidate.email) : candidate.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public static Candidate withEmail(EMail email) {
        if (email == null) {
            throw new IllegalStateException();
        }
        return new Candidate(email);
    }
}
