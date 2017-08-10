package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
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

        checkingService.addNewCheckinDate(new ParticipantID("1"),20);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        checkingService.addNewCheckinDate(new ParticipantID("1"),22);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        checkingService.addNewCheckinDate(new ParticipantID("1"),22);
        checkingService.addNewCheckinDate(new ParticipantID("2"),23);
        assertThat(checkingService.getColdFoodCount()).isEqualTo(2);
    }

    private class FakeCheckingStorage implements CheckingStorage {
        List<Checking> chekings;

        public FakeCheckingStorage() {
            this.chekings = new ArrayList<>();
        }

        @Override
        public void add(ParticipantID participantID) {
            chekings.add(new Checking(participantID));
        }

        @Override
        public int getCheckings() {
            return chekings.size();
        }
    }
}
