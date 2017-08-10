package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CheckingTest {
    CheckingStorage checkingStorage;
    CheckingService checkingService;

    @Before
    public void set_initialization() {
        checkingStorage = new FakeCheckingStorage();
        checkingService = new CheckingServiceImplementation(checkingStorage);
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkingService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        checkingService.addNewCheckinDate(new ParticipantId("1"),20);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        checkingService.addNewCheckinDate(new ParticipantId("1"),22);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        checkingService.addNewCheckinDate(new ParticipantId("1"),22);
        checkingService.addNewCheckinDate(new ParticipantId("2"),23);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        checkingService.addNewCheckinDate(new ParticipantId("1"), 22);
        checkingService.addNewCheckinDate(new ParticipantId("1"), 23);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(1);
    }

    private class FakeCheckingStorage implements CheckingStorage {
        Map<ParticipantId,Checking> chekings;

        public FakeCheckingStorage() {
            this.chekings = new HashMap<>();
        }

        @Override
        public void save(ParticipantId participantId) {

            chekings.put(participantId,new Checking(participantId));
        }

        @Override
        public List<Checking> getCheckings() {
            return new ArrayList<>(chekings.values());
        }
    }
}
