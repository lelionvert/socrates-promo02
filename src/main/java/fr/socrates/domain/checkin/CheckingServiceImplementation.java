package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo_14 on 09/08/2017.
 */
public class CheckingServiceImplementation implements CheckingService {
    List coldFood = new ArrayList();
    private CheckingStorage checkingStorage;

    public CheckingServiceImplementation() {
    }

    public CheckingServiceImplementation(CheckingStorage checkingStorage) {
        this.checkingStorage = checkingStorage;
    }

    @Override
    public int getColdFoodCount() {
        return this.checkingStorage.getCheckings().size();
    }

    @Override
    public void addNewCheckinDate(ParticipantId participantId, int hour) {
        LocalDateTime checkinDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            if (isHourValid(checkinDate)) this.checkingStorage.save(participantId);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkinDate);
            ex.printStackTrace();
        }
    }

    private boolean isHourValid(LocalDateTime checkinDate) {
        return checkinDate.getHour() >= Checking.COLD_FOOD_HOUR;
    }
}
