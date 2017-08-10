package fr.socrates.domain.checkin;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public interface CheckingService {

    public int getColdFoodCount();
    public void addNewCheckinDate(String participantID, int hour);
}
