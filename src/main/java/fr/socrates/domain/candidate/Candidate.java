package fr.socrates.domain.candidate;

class Candidate {
    private final EMail email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return email.equals(candidate.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return email.toString();
    }

    private Candidate(EMail email) {
        this.email = email;
    }

    public static Candidate withEmail(String email) {
        if (email == null) {
            throw new IllegalStateException();
        }
        return new Candidate(EMail.of(email));
    }
}
