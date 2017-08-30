package fr.socrates.domain.sponsor;

public class Siren {
    private final String siren;

    public Siren(String siren) {
        this.siren = siren;
    }

    public static Siren of(String siren) {
        return new Siren(siren);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Siren siren1 = (Siren) o;

        return siren.equals(siren1.siren);
    }

    @Override
    public int hashCode() {
        return siren.hashCode();
    }
}
