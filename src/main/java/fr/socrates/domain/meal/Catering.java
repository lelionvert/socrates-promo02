package fr.socrates.domain.meal;

import java.util.Map;

public class Catering {

    private final Map<DietOrder, Quantity> coversByDietByDay;

    public Catering(Map<DietOrder, Quantity> coversByDietByDay) {
        this.coversByDietByDay = coversByDietByDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catering catering = (Catering) o;

        return coversByDietByDay.equals(catering.coversByDietByDay);
    }

    @Override
    public int hashCode() {
        return coversByDietByDay.hashCode();
    }

    @Override
    public String toString() {
        return "Catering{" +
                "coversByDietByDay=" + coversByDietByDay +
                '}';
    }

    public Quantity numberOfCover(MealTime mealTime, Diet diet) {
        return this.coversByDietByDay.get(new DietOrder(mealTime, diet));
    }
}
