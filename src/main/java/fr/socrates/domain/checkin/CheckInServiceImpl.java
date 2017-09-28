package fr.socrates.domain.checkin;

import org.springframework.stereotype.Service;
import fr.socrates.domain.CandidateId;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
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
    public CheckIn addNewCheckIn(CheckIn checkIn) {
        return this.checkInRepository.save(checkIn);
    }

    @Override
    public boolean isCandidatePresentAt(CandidateId candidateId, LocalDateTime dateTime) {
        Optional<CheckIn> foundCheckIn = checkInRepository.getCheckInOf(candidateId);
        return foundCheckIn.map(checkIn -> checkIn.wasBefore(dateTime)).orElse(false);
    }

}
