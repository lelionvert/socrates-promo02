package fr.socrates.domain.meal;

public class DietOrder {
    private Diet diet;
    private MealTime mealTime;

    public DietOrder(MealTime mealTime, Diet diet) {
        this.mealTime = mealTime;
        this.diet = diet;
    }

    @Override
    public String toString() {
        return "DietOrder{" +
                "diet=" + diet +
                ", mealTime=" + mealTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DietOrder dietOrder = (DietOrder) o;

        if (diet != dietOrder.diet) return false;
        return mealTime == dietOrder.mealTime;
    }

    @Override
    public int hashCode() {
        int result = diet != null ? diet.hashCode() : 0;
        result = 31 * result + (mealTime != null ? mealTime.hashCode() : 0);
        return result;
    }
}
