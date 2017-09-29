package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.checkin.CheckInService;

import java.util.*;
import java.util.stream.Collectors;

public class MealServiceImpl implements MealService {
    private final CheckInService checkInService;
    private final ConfirmationService confirmationService;
    private static final int COLD_FOOD_HOUR = 21;

    public MealServiceImpl(CheckInService checkInService, ConfirmationService confirmationService) {
        this.checkInService = checkInService;
        this.confirmationService = confirmationService;
    }

    @Override
    public long countColdMeal() {
        return checkInService.countCheckinAfter(COLD_FOOD_HOUR);
    }

    @Override
    public Catering generateOrder() {
        List<Candidate> attendees = confirmationService.getAttendee();

        if (attendees.isEmpty())
            return new Catering(Collections.emptyMap());

        List<MealTime> mealTimes = Arrays.asList(MealTime.values());

        Map<Diet, List<Candidate>> dietPerListOfDiet = getMapDietPerListOfDiet(attendees);

        Map<DietOrder, Quantity> coversByDietByDay = new HashMap<>();
        for (Map.Entry<Diet, List<Candidate>> diet : dietPerListOfDiet.entrySet()) {
            for (MealTime mealTime : mealTimes) {
                DietOrder dietOrder = new DietOrder(mealTime, diet.getKey());
                List<Candidate> candidates = diet.getValue();
                Quantity quantity = Quantity.of(candidates.stream()
                        .filter(candidate -> checkInService.isCandidatePresentAt(candidate.getCandidateId(), mealTime.getDateTime()))
                        .count());
                coversByDietByDay.put(dietOrder, quantity);
            }
        }
        return new Catering(coversByDietByDay);
    }

    private Map<Diet, List<Candidate>> getMapDietPerListOfDiet(List<Candidate> attendees) {
        return attendees.stream()
                .collect(Collectors.groupingBy(Candidate::getDiet));
    }
}
