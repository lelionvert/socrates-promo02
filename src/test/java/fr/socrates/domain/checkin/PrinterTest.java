package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PrinterTest {
    private CheckInService checkInService;

    @Before
    public void setUp() throws Exception {
        checkInService = new CheckInServiceImpl(new FakeCheckingConnector(), new FakePrinter());
    }

    @Test
    public void should_print_zero_cold_meal_when_no_cold_meal() {
        assertThat(checkInService.print()).isEqualTo("No participant for the cold meal");
    }

    @Test
    public void should_print_one_cold_meal_when_one_cold_meal() {
        checkInService.addNewCheckinDate(new ParticipantId("1"),22);
        assertThat(checkInService.print()).isEqualTo("1 participants for the cold meal");
    }
}
