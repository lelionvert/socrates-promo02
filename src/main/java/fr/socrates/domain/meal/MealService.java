package fr.socrates.domain.meal;

import java.util.Map;

public interface MealService {
    long countColdMeal();


    Map<MealTime, Map<Diet, Long>> getCoversByDiet();

    Catering generateOrder();
}
