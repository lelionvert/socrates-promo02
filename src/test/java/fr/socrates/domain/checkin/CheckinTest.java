package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CheckinTest {
    CheckinConnector checkinConnector;
    CheckinService checkinService;

    @Before
    public void set_initialization() {
        checkinConnector = new FakeCheckingConnector();
        checkinService = new CheckinServiceImplementation(checkinConnector, new FakePrinter());
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkinService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        checkinService.addNewCheckinDate(new ParticipantId("1"),20);
        assertThat(checkinService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        checkinService.addNewCheckinDate(new ParticipantId("1"),22);
        assertThat(checkinService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        checkinService.addNewCheckinDate(new ParticipantId("1"),22);
        checkinService.addNewCheckinDate(new ParticipantId("2"),23);
        assertThat(checkinService.getColdFoodCount()).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        checkinService.addNewCheckinDate(new ParticipantId("1"), 22);
        checkinService.addNewCheckinDate(new ParticipantId("1"), 23);
        assertThat(checkinService.getColdFoodCount()).isEqualTo(1);
    }

}
