package fr.socrates.domain.meal;

import java.util.Map;

public interface MealService {
    long countColdMeal();


    @Deprecated
    Map<MealTime, Map<Diet, Long>> getCoversByDiet();

    Catering generateOrder();

}
