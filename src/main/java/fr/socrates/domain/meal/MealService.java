package fr.socrates.domain.meal;

interface MealService {
    long countColdMeal();

    Catering generateOrder();

}
