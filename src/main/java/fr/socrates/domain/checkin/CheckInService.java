package fr.socrates.domain.checkin;

public interface CheckInService {
    long getColdFoodCount();
    boolean addNewCheckIn(CheckIn checkIn);
    void print();
}
