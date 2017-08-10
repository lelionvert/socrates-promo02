package fr.socrates.domain.checkin;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class Checking {

    public static final int COLD_FOOD_HOUR = 21;
    private ParticipantID participantID;

    public Checking(ParticipantID participantID) {
        this.participantID = participantID;
    }
}
