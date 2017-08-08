package fr.socrates.domain.checkin;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class TestCheckin {
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
    public void should_return_an_empty_list_when_add_participant_after_twenty_one() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-2017 10:20:56";
        try {
            checkin.addCheckinParticipant(sdf.parse(dateInString),1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertThat(checkin.getColdFoodCount()).isEmpty();
    }
}
