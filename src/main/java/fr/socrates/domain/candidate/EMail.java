package fr.socrates.domain.candidate;

public class EMail {
    private final String email;

    private EMail(String email) {
        this.email = email;
    }

    static EMail of(String email) {
        if (email == null) {
            throw new IllegalStateException();
        }
        return new EMail(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EMail eMail = (EMail) o;

        return email.equals(eMail.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
