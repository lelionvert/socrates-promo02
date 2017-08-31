package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

import java.util.Optional;

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
    public boolean doesCandidateArriveAfter(CandidateId candidateId, int hour) {
        Optional<CheckIn> foundCheckIn = checkInRepository.getCheckInOf(candidateId);
        if(foundCheckIn.isPresent())
            return foundCheckIn.get().getCheckInHour() > hour;
        return false;
    }

}
