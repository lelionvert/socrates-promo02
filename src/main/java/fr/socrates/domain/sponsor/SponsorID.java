package fr.socrates.domain.sponsor;

public class SponsorID {

    private String id;

    public SponsorID(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SponsorID sponsorID = (SponsorID) o;

        return id != null ? id.equals(sponsorID.id) : sponsorID.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }
}
