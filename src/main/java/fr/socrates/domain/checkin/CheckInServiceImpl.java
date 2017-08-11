package fr.socrates.domain.checkin;

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
                .filter(checkIn -> checkIn.isCheckInDateForColdMeal())
                .count();
    }

    @Override
    public void addNewCheckIn(CheckIn checkIn) {
        this.checkInRepository.save(checkIn);
    }

    @Override
    public String print() {
        return printer.print(getColdFoodCount());
    }
}
