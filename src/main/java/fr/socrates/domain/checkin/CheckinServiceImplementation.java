package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_14 on 09/08/2017.
 */
public class CheckinServiceImplementation implements CheckinService {
    List coldFood = new ArrayList();
    private CheckinStorage checkinStorage;

    public CheckinServiceImplementation() {
    }

    public CheckinServiceImplementation(CheckinStorage checkinStorage) {
        this.checkinStorage = checkinStorage;
    }

    @Override
    public int getColdFoodCount() {
        return this.checkinStorage.getCheckings().size();
    }

    @Override
    public void addNewCheckinDate(ParticipantId participantId, int hour) {
        LocalDateTime checkinDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            if (isHourValid(checkinDate)) this.checkinStorage.save(participantId);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkinDate);
            ex.printStackTrace();
        }
    }

    private boolean isHourValid(LocalDateTime checkinDate) {
        return checkinDate.getHour() >= Checkin.COLD_FOOD_HOUR;
    }
}
