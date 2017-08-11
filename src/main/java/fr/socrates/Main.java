package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.checkin.*;
import fr.socrates.infra.repositories.ConsolePrinter;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    // main argument ==> john=2017-12-03T23:15:30 houssam=2017-12-03T20:15:30
    public static void main(String[] args) {
        CheckInRepository inMemoryCheckInRepository = new InMemoryCheckInRepository();
        Printer consolePrinter = new ConsolePrinter();
        CheckInService checkInService = new CheckInServiceImpl(inMemoryCheckInRepository, consolePrinter);

        for (String argCheckIn : args) {
            String[] checkInArgument = argCheckIn.split("=");
            ParticipantId participantId = new ParticipantId(checkInArgument[0]);
            LocalDateTime checkInDateTime = LocalDateTime.parse(checkInArgument[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            CheckIn checkIn = new CheckIn(participantId, checkInDateTime);
            checkInService.addNewCheckIn(checkIn);
        }

        checkInService.print();
    }
}
