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
    public long countCheckinAfter(int hour) {
        return this.checkInRepository
                .getCheckIns()
                .stream()
                .filter(checkIn -> checkIn.getCheckInHour() >= hour)
                .count();
    }

    @Override
    public boolean addNewCheckIn(CheckIn checkIn) {
        return this.checkInRepository.save(checkIn);
    }

}
