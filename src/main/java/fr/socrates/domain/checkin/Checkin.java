package fr.socrates.domain.checkin;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class Checkin {

    private static final int COLD_FOOD_HOUR = 21;
    List coldFood = new ArrayList();

    public List getColdFoodCount() {
        return coldFood;
    }

    public void addCheckinParticipant(int participantID, LocalDateTime checkinDate) {
        if (checkinDate.getHour()>= COLD_FOOD_HOUR) {
            coldFood.add(participantID);
        }
    }
}
