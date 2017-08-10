package fr.socrates.domain.checkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo_14 on 10/08/2017.
 */
class FakeCheckingConnector implements CheckinConnector {
    Map<ParticipantId, Checkin> chekings;

    public FakeCheckingConnector() {
        this.chekings = new HashMap<>();
    }

    @Override
    public void save(ParticipantId participantId) {

        chekings.put(participantId, new Checkin(participantId));
    }

    @Override
    public List<Checkin> getCheckings() {
        return new ArrayList<>(chekings.values());
    }
}
