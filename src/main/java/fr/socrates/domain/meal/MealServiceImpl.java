package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.checkin.CheckInService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealServiceImpl implements MealService {
    private final CheckInService checkInService;
    private ConfirmationService confirmationService;
    private final int COLD_FOOD_HOUR = 21;

    public MealServiceImpl(CheckInService checkInService, ConfirmationService confirmationService) {
        this.checkInService = checkInService;
        this.confirmationService = confirmationService;
    }

    @Override
    public long countColdMeal() {
        return checkInService.countCheckinAfter(COLD_FOOD_HOUR);
    }


    @Override
    public Map<MealTime, Map<Diet, Long>> getCoversByDiet() {
        List<Candidate> attendees = confirmationService.getListAttendee();
        Map<MealTime, Map<Diet, Long>> covers = new HashMap<>();

        for (MealTime mealTime : MealTime.values()) {
            Map<Diet, Long> coversForMealTime = new HashMap<>();
            for (Diet diet : Diet.values()) {
                coversForMealTime.put(diet,
                        attendees.stream()
                                .map(Candidate::getDiet)
                                .filter(attendeeDiet -> attendeeDiet.equals(diet))
                                .count());
            }
            covers.put(mealTime, coversForMealTime);
        }
        return covers;
    }
}
