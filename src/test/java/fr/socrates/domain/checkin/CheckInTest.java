package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CheckInTest {
    private CheckInService checkInService;

    @Before
    public void set_initialization() {
        CheckInConnector checkInConnector = new FakeCheckingConnector();
        checkInService = new CheckInServiceImpl(checkInConnector, new FakePrinter());
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"),20);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"),22);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"),22);
        checkInService.addNewCheckInDate(new ParticipantId("2"),23);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        checkInService.addNewCheckInDate(new ParticipantId("1"), 22);
        checkInService.addNewCheckInDate(new ParticipantId("1"), 23);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

}
