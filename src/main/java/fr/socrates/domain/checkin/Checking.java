package fr.socrates.domain.checkin;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class Checking {

    public static final int COLD_FOOD_HOUR = 21;
    private ParticipantId participantId;

    public Checking(ParticipantId participantId) {
        this.participantId = participantId;
    }
}
