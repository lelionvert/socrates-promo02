package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class CheckInTest {
    private CheckInService checkInService;

    @Before
    public void set_initialization() {
        CheckInRepository checkInRepository = new FakeCheckInRepository();
        checkInService = new CheckInServiceImpl(checkInRepository, new FakePrinter());
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"), dateTimeFirstDay(20, 0));
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        checkInService.addNewCheckInDate(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        checkInService.addNewCheckInDate(new ParticipantId("2"), dateTimeFirstDay(23, 0));
        assertThat(checkInService.getColdFoodCount()).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        checkInService.addNewCheckInDate(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        checkInService.addNewCheckInDate(new ParticipantId("1"), dateTimeFirstDay(23, 0));
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_print_zero_cold_meal_when_no_cold_meal() {
        assertThat(checkInService.print()).isEqualTo("No participant for the cold meal");
    }

    @Test
    public void should_print_one_cold_meal_when_one_cold_meal() {
        checkInService.addNewCheckInDate(new ParticipantId("1"),dateTimeFirstDay(22, 0));
        assertThat(checkInService.print()).isEqualTo("1 participant(s) for the cold meal");
    }

    private LocalDateTime dateTimeFirstDay(int hour, int minutes) {
        return LocalDateTime.of(2017, Month.OCTOBER, 20, hour, minutes);
    }
}
