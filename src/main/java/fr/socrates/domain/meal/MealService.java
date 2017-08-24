package fr.socrates.domain.meal;

import java.util.Map;

public interface MealService {
    long countColdMeal();

    void printAllMeals();

    Map<MealTime, Map<Diet, Long>> getCoversByDiet();
}
