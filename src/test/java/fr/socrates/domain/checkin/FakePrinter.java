package fr.socrates.domain.checkin;

public class FakePrinter implements Printer {
    @Override
    public String print(int coldFoodCount) {
        if (coldFoodCount == 0)
            return "No participant for the cold meal";
        else
            return coldFoodCount + " participant(s) for the cold meal";
    }
}
