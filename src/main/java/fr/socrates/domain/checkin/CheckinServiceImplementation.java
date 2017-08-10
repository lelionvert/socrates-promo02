package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckinServiceImplementation implements CheckinService {
    List coldFood = new ArrayList();
    private CheckinConnector checkinConnector;
    private Printer printer;

    public CheckinServiceImplementation(CheckinConnector checkinConnector, Printer printer) {
        this.checkinConnector = checkinConnector;
        this.printer = printer;
    }

    @Override
    public int getColdFoodCount() {
        return this.checkinConnector.getCheckings().size();
    }

    @Override
    public void addNewCheckinDate(ParticipantId participantId, int hour) {
        LocalDateTime checkinDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            if (isHourValid(checkinDate)) this.checkinConnector.save(participantId);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkinDate);
            ex.printStackTrace();
        }
    }

    @Override
    public String print() {
        return printer.print(getColdFoodCount());
    }

    private boolean isHourValid(LocalDateTime checkinDate) {
        return checkinDate.getHour() >= Checkin.COLD_FOOD_HOUR;
    }
}
