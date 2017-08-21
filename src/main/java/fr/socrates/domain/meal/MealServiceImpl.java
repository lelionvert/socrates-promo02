package fr.socrates.domain.meal;

import fr.socrates.domain.checkin.CheckInService;

public class MealServiceImpl implements MealService {
    private final CheckInService checkInService;
    private final int COLD_FOOD_HOUR = 21;

    public MealServiceImpl(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @Override
    public long countColdMeal() {
        return checkInService.countCheckinAfter(COLD_FOOD_HOUR);
    }
}
