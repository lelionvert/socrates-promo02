package fr.socrates.domain.checkin;

public interface CheckInService {
    long countCheckinAfter(int hour);
    boolean addNewCheckIn(CheckIn checkIn);
}
