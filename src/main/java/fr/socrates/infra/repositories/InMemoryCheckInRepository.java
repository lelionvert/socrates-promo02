package fr.socrates.infra.repositories;

import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInRepository;
import fr.socrates.domain.CandidateId;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCheckInRepository implements CheckInRepository {
    private final Map<CandidateId, CheckIn> checkIns;

    public InMemoryCheckInRepository() {
        this.checkIns = new HashMap<>();
    }

    @Override
    public CheckIn save(CheckIn checkIn) {
        return checkIns.put(checkIn.getCandidateId(), checkIn);
    }

    @Override
    public List<CheckIn> getCheckIns() {
        return new ArrayList<>(checkIns.values());
    }

    @Override
    public Optional<CheckIn> getCheckInOf(CandidateId candidateId) {
        return  Optional.ofNullable(checkIns.get(candidateId));
    }
}
