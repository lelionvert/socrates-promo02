package fr.socrates.domain.checkin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public interface CheckinService {

    public List getColdFoodCount();

    public void addCheckinParticipant(int participantID, LocalDateTime checkinDate);
}
