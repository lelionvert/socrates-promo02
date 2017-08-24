package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateBuilder;
import fr.socrates.domain.checkin.CheckInService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {
    private MealService mealService;

    @Mock
    private CheckInService checkInService;

    @Mock
    private ConfirmationService confirmationService;

    @Before
    public void setUp() throws Exception {
        mealService = new MealServiceImpl(checkInService, confirmationService);
    }

    @Test
    public void should_get_list_of_covers_in_map() throws Exception {
        List<Candidate> attendees = new ArrayList<>();
        attendees.add(new CandidateBuilder().withEmail("kara@doc.fr").createCandidate());
        attendees.add(new CandidateBuilder().withEmail("perse@val.fr").createCandidate());
        attendees.add(new CandidateBuilder().withEmail("kara@doc.fr").createCandidate());

        Mockito.doReturn(attendees).when(confirmationService).getListAttendee();

        Assertions.assertThat(mealService.getCoversByDiet().get(MealTime.FRIDAY_NOON).get(Diet.NORMAL)).isEqualTo(3);
        Assertions.assertThat(mealService.getCoversByDiet().get(MealTime.FRIDAY_NIGHT).get(Diet.NORMAL)).isEqualTo(3);
        Assertions.assertThat(mealService.getCoversByDiet().get(MealTime.SATURDAY_NOON).get(Diet.NORMAL)).isEqualTo(3);
        Assertions.assertThat(mealService.getCoversByDiet().get(MealTime.SATURDAY_NIGHT).get(Diet.NORMAL)).isEqualTo(3);
        Assertions.assertThat(mealService.getCoversByDiet().get(MealTime.SUNDAY_NOON).get(Diet.NORMAL)).isEqualTo(3);
    }
}
