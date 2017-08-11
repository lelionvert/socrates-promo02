package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class CheckInServiceImpl implements CheckInService {
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
    public void addNewCheckInDate(ParticipantId participantId, int hour) {
        LocalDateTime checkInDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            if (isHourValid(checkInDate)) this.checkInConnector.save(new CheckIn(participantId));
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkInDate);
            ex.printStackTrace();
        }
    }

    @Override
    public String print() {
        return printer.print(getColdFoodCount());
    }

    private boolean isHourValid(LocalDateTime checkInDate) {
        return checkInDate.getHour() >= CheckIn.COLD_FOOD_HOUR;
    }
}
