package fr.socrates.domain.checkin;

public interface CheckInService {
    long getColdFoodCount();
    void addNewCheckIn(CheckIn checkIn);
    void print();
}
