package fr.socrates.domain.checkin;

interface CheckInService {
    long getColdFoodCount();
    void addNewCheckIn(CheckIn checkIn);
    String print();
}
