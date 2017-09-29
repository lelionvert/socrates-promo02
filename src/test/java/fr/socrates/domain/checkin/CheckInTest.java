package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.meal.MealTime;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckInTest {
    private CheckInService checkInService;

    @Before
    public void set_initialization() {
        CheckInRepository checkInRepository = new InMemoryCheckInRepository();
        checkInService = new CheckInServiceImpl(checkInRepository);
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(0);
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {
        final CheckIn checkIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(20));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(0);
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {
        CheckIn checkIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(22));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(1);
    }

    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {
        CheckIn checkIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(22));
        CheckIn otherCheckIn = new CheckIn(new CandidateId("2"), dateTimeFirstDay(23));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(2);
    }

    @Test
    public void should_return_one_result_when_two_time_same_participant_is_added() {
        CheckIn checkIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(22));
        CheckIn otherCheckIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(23));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(1);
    }

    @Test
    public void should_return_one_result_when_one_is_cold_meal_and_not_the_other() {
        CheckIn checkIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(19));
        CheckIn otherCheckIn = new CheckIn(new CandidateId("1"), dateTimeFirstDay(23));
        checkInService.addNewCheckIn(checkIn);
        checkInService.addNewCheckIn(otherCheckIn);
        assertThat(checkInService.countCheckinAfter(21)).isEqualTo(1);
    }

    @Test
    public void should_return_false_if_hour_of_arrival_of_attendee_is_after_twenty_one() {
        CandidateId candidateId = new CandidateId("1");
        CheckIn checkIn = new CheckIn(candidateId, dateTimeFirstDay(22));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.isCandidatePresentAt(candidateId, MealTime.THURSDAY_NIGHT.getDateTime())).isFalse();
    }

    @Test
    public void should_return_true_if_hour_of_arrival_of_attendee_is_before_twenty_one() {
        CandidateId candidateId = new CandidateId("1");
        CheckIn checkIn = new CheckIn(candidateId, dateTimeFirstDay(20));
        checkInService.addNewCheckIn(checkIn);
        assertThat(checkInService.isCandidatePresentAt(candidateId,MealTime.THURSDAY_NIGHT.getDateTime() )).isTrue();
    }

    private LocalDateTime dateTimeFirstDay(int hour) {
        return LocalDateTime.of(2017, Month.OCTOBER, 26, hour, 0);
    }
}
