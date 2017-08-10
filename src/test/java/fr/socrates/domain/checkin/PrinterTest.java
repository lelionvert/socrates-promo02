package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PrinterTest {
    CheckinService checkinService;

    @Before
    public void setUp() throws Exception {
        checkinService = new CheckinServiceImplementation(new FakeCheckingConnector(), new FakePrinter());
    }

    @Test
    public void should_print_zero_cold_meal_when_no_cold_meal() {
        assertThat(checkinService.print()).isEqualTo("No participant for the cold meal");
    }

    @Test
    public void should_print_one_cold_meal_when_one_cold_meal() {
        checkinService.addNewCheckinDate(new ParticipantId("1"),22);
        assertThat(checkinService.print()).isEqualTo("1 participants for the cold meal");
    }
}
