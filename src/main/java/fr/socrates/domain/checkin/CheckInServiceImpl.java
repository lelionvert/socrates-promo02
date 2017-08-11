package fr.socrates.domain.checkin;

import fr.socrates.common.Printer;

public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final Printer printer;

    public CheckInServiceImpl(CheckInRepository checkInRepository, Printer printer) {
        this.checkInRepository = checkInRepository;
        this.printer = printer;
    }

    @Override
    public long getColdFoodCount() {
        return this.checkInRepository
                .getCheckIns()
                .stream()
                .filter(CheckIn::isCheckInDateForColdMeal)
                .count();
    }

    @Override
    public void addNewCheckIn(CheckIn checkIn) {
        this.checkInRepository.save(checkIn);
    }

    @Override
    public void print() {
        if (getColdFoodCount() > 0)
            printer.print(getColdFoodCount() + " participant(s) for the cold meal");
        else
            printer.print("No participant for the cold meal");
    }
}
