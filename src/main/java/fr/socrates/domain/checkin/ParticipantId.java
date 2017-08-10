package fr.socrates.domain.checkin;

/**
 * Created by lenovo_14 on 10/08/2017.
 */
public class ParticipantId {
    private String id;

    public ParticipantId(String s) {
        id = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipantId that = (ParticipantId) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
