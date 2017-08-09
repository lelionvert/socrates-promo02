package fr.socrates.domain.checkin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_14 on 09/08/2017.
 */
public class CheckinServiceImplementation implements CheckinService {

    List coldFood = new ArrayList();

    @Override
    public List getColdFoodCount() {
        return coldFood;
    }

    @Override
    public void addCheckinParticipant(int participantID, LocalDateTime checkinDate) {
        if (checkinDate.getHour() >= Checkin.COLD_FOOD_HOUR) {
            coldFood.add(participantID);
        }
    }


}
