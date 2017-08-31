package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateBuilder;
import fr.socrates.domain.checkin.CheckInService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

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
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        Map<DietOrder, Quantity> coversByDietByDay = new HashMap<>();
        addDiet(coversByDietByDay, Diet.NORMAL);
        Catering expectedCateringOrder = new Catering(coversByDietByDay);
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).createCandidate(),
                new CandidateBuilder().withEmail("perce@val.fr").withDiet(Diet.VEGAN).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        Map<DietOrder, Quantity> coversByDietByDay = new HashMap<>();
        addDiet(coversByDietByDay, Diet.NORMAL);
        addDiet(coversByDietByDay, Diet.VEGAN);
        Catering expectedCateringOrder = new Catering(coversByDietByDay);
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees_except_thursday_for_one_attendee() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new CandidateBuilder().withEmail("kara@doc.fr").withDiet(Diet.NORMAL).createCandidate(),
                new CandidateBuilder().withEmail("perce@val.fr").withDiet(Diet.VEGAN).createCandidate());
        when(confirmationService.getListAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        Map<DietOrder, Quantity> coversByDietByDay = new HashMap<>();
        addDiet(coversByDietByDay, Diet.NORMAL);
        addDiet(coversByDietByDay, Diet.VEGAN);
        Catering expectedCateringOrder = new Catering(coversByDietByDay);
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }

    private void addDiet(Map<DietOrder, Quantity> coversByDietByDay, Diet diet) {
        coversByDietByDay.put(new DietOrder(MealTime.THURSDAY_NIGHT, diet), Quantity.of(1));
        coversByDietByDay.put(new DietOrder(MealTime.FRIDAY_NOON, diet), Quantity.of(1));
        coversByDietByDay.put(new DietOrder(MealTime.FRIDAY_NIGHT, diet), Quantity.of(1));
        coversByDietByDay.put(new DietOrder(MealTime.SATURDAY_NOON, diet), Quantity.of(1));
        coversByDietByDay.put(new DietOrder(MealTime.SATURDAY_NIGHT, diet), Quantity.of(1));
        coversByDietByDay.put(new DietOrder(MealTime.SUNDAY_NOON, diet), Quantity.of(1));
    }

}
