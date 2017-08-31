package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.checkin.CheckInService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MealServiceImpl implements MealService {
    private final CheckInService checkInService;
    private ConfirmationService confirmationService;
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

    @Override
    public Catering generateOrder() {
        List<Candidate> attendees = confirmationService.getListAttendee();

        if (attendees.isEmpty())
            return new Catering(Collections.emptyMap());

        List<MealTime> mealTimes = Arrays.asList(MealTime.values());

        Map<Diet, List<Diet>> dietPerListOfDiet = getMapDietPerListOfDiet(attendees);

        Map<DietOrder, Quantity> coversByDietByDay = Collections.emptyMap();
        for (Map.Entry<Diet, List<Diet>> diet : dietPerListOfDiet.entrySet()) {
            for (MealTime mealTime : mealTimes) {
                Map<DietOrder, Quantity> dietOrder = createDietOrder(diet, mealTime);
                coversByDietByDay = concat(coversByDietByDay, dietOrder);
            }
        }

        return new Catering(coversByDietByDay);
    }

    private Map<DietOrder, Quantity> createDietOrder(Map.Entry<Diet, List<Diet>> diet, MealTime mealTime) {
        DietOrder dietOrder = new DietOrder(mealTime, diet.getKey());
        Quantity quantity = Quantity.of(diet.getValue().size());
        return Collections.singletonMap(dietOrder, quantity);
    }

    private static Map<DietOrder, Quantity> concat(Map<DietOrder, Quantity> map1, Map<DietOrder, Quantity> map2) {
        Map<DietOrder, Quantity> newMap = new HashMap<>();
        newMap.putAll(map1);
        newMap.putAll(map2);
        return newMap;
    }

    private Map<Diet, List<Diet>> getMapDietPerListOfDiet(List<Candidate> attendees) {
        return attendees.stream()
                .map(Candidate::getDiet)
                .collect(Collectors.groupingBy(Function.identity()));
    }
}
