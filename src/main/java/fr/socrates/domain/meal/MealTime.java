package fr.socrates.domain.meal;

import java.time.LocalDateTime;

public enum MealTime {
    THURSDAY_NIGHT(26, 21), FRIDAY_NOON(27, 13), FRIDAY_NIGHT(27, 21), SATURDAY_NOON(28, 13), SATURDAY_NIGHT(28, 21), SUNDAY_NOON(29, 13);

    private LocalDateTime dateTime;

    MealTime(int day, int mealLimitHour) {
        dateTime = LocalDateTime.of(2017, 10, day, mealLimitHour, 0);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
