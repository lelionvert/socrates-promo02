package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInRepository;
import fr.socrates.domain.CandidateId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCheckInRepository implements CheckInRepository {
    private final Map<CandidateId, CheckIn> checkIns;

    public InMemoryCheckInRepository() {
        this.checkIns = new HashMap<>();
    }

    @Override
    public boolean save(CheckIn checkin) {
        return checkIns.put(checkin.getCandidateId(), checkin) != null;
    }

    @Override
    public List<CheckIn> getCheckIns() {
        return new ArrayList<>(checkIns.values());
    }
}
