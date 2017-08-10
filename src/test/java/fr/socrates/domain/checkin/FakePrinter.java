package fr.socrates.domain.checkin;

/**
 * Created by lenovo_14 on 10/08/2017.
 */
public class FakePrinter implements Printer {
    @Override
    public String print(int coldFoodCount) {
        if (coldFoodCount == 0)
            return "No participant for the cold meal";
        else
            return coldFoodCount + " participants for the cold meal";
    }
}
