package fr.socrates.infra.repositories;

import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInRepository;
import fr.socrates.domain.checkin.ParticipantId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCheckInRepository implements CheckInRepository {
    private final Map<ParticipantId, CheckIn> checkIns;

    public InMemoryCheckInRepository() {
        this.checkIns = new HashMap<>();
    }

    @Override
    public void save(CheckIn checkin) {
        checkIns.put(checkin.getParticipantId(), checkin);
    }

    @Override
    public List<CheckIn> getCheckIns() {
        return new ArrayList<>(checkIns.values());
    }
}
