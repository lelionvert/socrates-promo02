package fr.socrates.domain.meal;

public interface MealService {
    long countColdMeal();

    Catering generateOrder();

}
