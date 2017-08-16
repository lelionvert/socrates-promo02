package fr.socrates.domain.checkin;

import fr.socrates.common.FakePrinter;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class CheckInTest {
    private CheckInService checkInService;
    private FakePrinter printer;

    @Before
    public void set_initialization() {
        CheckInRepository checkInRepository = new InMemoryCheckInRepository();
        printer = new FakePrinter();
        checkInService = new CheckInServiceImpl(checkInRepository, printer);
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        final CheckIn checkIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(20, 0));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        CheckIn checkIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        CheckIn checkIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        CheckIn otherCheckIn = new CheckIn(new ParticipantId("2"), dateTimeFirstDay(23, 0));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        CheckIn checkIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(22, 0));
        CheckIn otherCheckIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(23, 0));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_return_one_result_when_one_is_cold_meal_and_not_the_other() {
        CheckIn checkIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(19, 0));
        CheckIn otherCheckIn = new CheckIn(new ParticipantId("1"), dateTimeFirstDay(23, 0));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.getColdFoodCount()).isEqualTo(1);
    }

    @Test
    public void should_print_zero_cold_meal_when_no_cold_meal() {
        checkInService.print();
        assertThat(printer.flush()).isEqualTo("No participant for the cold meal");
    }

    @Test
    public void should_print_one_cold_meal_when_one_cold_meal() {
        CheckIn checkIn = new CheckIn(new ParticipantId("1"),dateTimeFirstDay(22, 0));
        checkInService.addNewCheckIn(checkIn);
        checkInService.print();
        assertThat(printer.flush()).isEqualTo("1 participant(s) for the cold meal");
    }

    private LocalDateTime dateTimeFirstDay(int hour, int minutes) {
        return LocalDateTime.of(2017, Month.OCTOBER, 20, hour, minutes);
    }
}
