package fr.socrates.domain.checkin;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final Printer printer;

    public CheckInServiceImpl(CheckInRepository checkInRepository, Printer printer) {
        this.checkInRepository = checkInRepository;
        this.printer = printer;
    }

    @Override
    public int getColdFoodCount() {
        return this.checkInRepository.getCheckIns().size();
    }

    @Override
    public String print() {
        return printer.print(getColdFoodCount());
    }

    @Override
    public void addNewCheckInDate(ParticipantId participantId, LocalDateTime checkInDate) {
        try {
            if (isHourValid(checkInDate)) this.checkInRepository.save(new CheckIn(participantId));
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkInDate);
            ex.printStackTrace();
        }
    }

    private boolean isHourValid(LocalDateTime checkInDate) {
        return checkInDate.getHour() >= COLD_FOOD_HOUR;
    }
}
