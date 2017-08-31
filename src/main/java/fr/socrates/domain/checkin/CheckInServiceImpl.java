package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;

    public CheckInServiceImpl(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
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

    @Override
    public long getTimeOfArrivalOf(CandidateId candidateId) {
        return 19;
    }

}
