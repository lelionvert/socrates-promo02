package fr.socrates.domain.meal;

import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.checkin.CheckInService;

import java.util.*;
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
    @Deprecated
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

        Map<Diet, Long> dietPerListOfDiet = getMapDietPerListOfDiet(attendees);


        Map<DietOrder, Quantity> coversByDietByDay = new HashMap<>();
        for (MealTime mealTime : mealTimes) {
            for (Map.Entry<Diet, Long> diet : dietPerListOfDiet.entrySet()) {
                DietOrder dietOrder = new DietOrder(mealTime, diet.getKey());
                Quantity quantity = Quantity.of(diet.getValue());
                coversByDietByDay.put(dietOrder, quantity);
            }
        }

        adjustForThursday(coversByDietByDay,attendees);
        return new Catering(coversByDietByDay);
    }

    private void adjustForThursday(Map<DietOrder, Quantity> coversByDietByDay,List<Candidate> attendees) {
        for (Candidate attendee : attendees) {
            boolean eatColdThursday = checkInService.doesCandidateArriveAfter(attendee.getCandidateId(), COLD_FOOD_HOUR);
            if(eatColdThursday)
            {
                Diet diet = attendee.getDiet();
                Quantity quantity = coversByDietByDay.get(new DietOrder(MealTime.THURSDAY_NIGHT,diet));
                coversByDietByDay.put(new DietOrder(MealTime.THURSDAY_NIGHT,diet),Quantity.of(quantity.getQuantity()-1));
            }
        }

    }

    private Map<DietOrder, Quantity> createDietOrder(Map.Entry<Diet,Long> diet, MealTime mealTime) {
        DietOrder dietOrder = new DietOrder(mealTime, diet.getKey());
        Quantity quantity = Quantity.of(diet.getValue());
        return Collections.singletonMap(dietOrder, quantity);
    }

    private Map<Diet, Long> getMapDietPerListOfDiet(List<Candidate> attendees) {
        return attendees.stream()
                .collect(Collectors.groupingBy(attendee -> attendee.getDiet(), Collectors.counting()));
    }
}
