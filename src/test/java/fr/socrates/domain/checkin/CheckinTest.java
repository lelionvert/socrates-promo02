package fr.socrates.domain.checkin;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class CheckinTest {
    Checkin checkin;

    @Before
    public void set_initialization() {
        checkin = new Checkin();
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(checkin.getColdFoodCount()).isEmpty();
    }

    @Test
    public void should_return_an_empty_list_when_add_participant_before_twenty_one() {

        addNewCheckinDate(20);

        assertThat(checkin.getColdFoodCount()).isEmpty();
    }

    @Test
    public void should_return_one_result_list_when_add_participant_after_twenty_one() {


        addNewCheckinDate(22);
        assertThat(checkin.getColdFoodCount()).hasSize(1);
    }


    @Test
    public void should_return_two_result_list_when_add_participant_after_twenty_one() {


        addNewCheckinDate(22);
        addNewCheckinDate(23);
        assertThat(checkin.getColdFoodCount()).hasSize(2);
    }

    public void addNewCheckinDate(int hour)
    {
        LocalDateTime checkinDate = LocalDateTime.of(2017, 8, 12, hour, 14);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            checkin.addCheckinParticipant(1, checkinDate);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", checkinDate);
            ex.printStackTrace();
        }

    }

}
