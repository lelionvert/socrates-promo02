package fr.socrates.domain.meal;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateBuilder;
import fr.socrates.domain.checkin.CheckInService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static fr.socrates.domain.meal.Diet.NORMAL;
import static fr.socrates.domain.meal.Diet.PESCATARIAN;
import static fr.socrates.domain.meal.Diet.VEGAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        when(checkInService.isCandidatePresentAt(Mockito.any(),Mockito.any())).thenReturn(true);
    }

    @Test
    public void should_no_produce_an_order_without_attendes() throws Exception {
        when(confirmationService.getListAttendee()).thenReturn(Collections.emptyList());

        Catering cateringOrdering = mealService.generateOrder();

        Catering expectedCateringOrder = new Catering(Collections.emptyMap());
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_when_only_one_attendee() throws Exception {
        List<Candidate> attendees = Collections.singletonList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).withCandidateId(new CandidateId("1")).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).withCandidateId(new CandidateId("1")).createCandidate(),
                new CandidateBuilder().withEmail("perce@val.fr").withDiet(VEGAN).withCandidateId(new CandidateId("2")).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));

    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees_same_diet() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(VEGAN).withCandidateId(new CandidateId("1")).createCandidate(),
                new CandidateBuilder().withEmail("perce@val.fr").withDiet(VEGAN).withCandidateId(new CandidateId("2")).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees_except_thursday_for_one_attendee() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).withCandidateId(new CandidateId("1")).createCandidate(),
                new CandidateBuilder().withEmail("perce@val.fr").withDiet(VEGAN).withCandidateId(new CandidateId("2")).createCandidate(),
                new CandidateBuilder().withEmail("dame@seli.fr").withDiet(VEGAN).withCandidateId(new CandidateId("3")).createCandidate(),
                new CandidateBuilder().withEmail("roi@loth.fr").withDiet(VEGAN).withCandidateId(new CandidateId("4")).createCandidate(),
                new CandidateBuilder().withEmail("bo@hort.fr").withDiet(Diet.PESCATARIAN).withCandidateId(new CandidateId("5")).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        when(checkInService.isCandidatePresentAt(attendees.get(1).getCandidateId(), MealTime.THURSDAY_NIGHT.getDateTime())).thenReturn(false);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, PESCATARIAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        Catering expectedCateringOrder = new Catering(Collections.emptyMap());
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }
}
