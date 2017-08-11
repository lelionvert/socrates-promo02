package fr.socrates.domain.checkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FakeCheckInRepository implements CheckInRepository {
    private final Map<ParticipantId, CheckIn> checkIns;

    FakeCheckInRepository() {
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
