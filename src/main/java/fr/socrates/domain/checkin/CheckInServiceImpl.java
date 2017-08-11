package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckInServiceImpl implements CheckInService {
    List coldFood = new ArrayList();
    private final CheckInConnector checkInConnector;
    private final Printer printer;

    public CheckInServiceImpl(CheckInConnector checkInConnector, Printer printer) {
        this.checkInConnector = checkInConnector;
        this.printer = printer;
    }

    @Override
    public int getColdFoodCount() {
        return this.checkInConnector.getCheckIns().size();
    }

    @Override
    public void addNewCheckinDate(ParticipantId participantId, int hour) {
        LocalDateTime checkinDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            if (isHourValid(checkinDate)) this.checkInConnector.save(new CheckIn(participantId));
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
        return checkinDate.getHour() >= CheckIn.COLD_FOOD_HOUR;
    }
}
