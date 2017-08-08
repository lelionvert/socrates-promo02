package fr.socrates.domain.checkin;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lenovo_14 on 08/08/2017.
 */
public class TestCheckin {

    @Test

    public void should_return_an_empty_list() {
        Checkin checkin= new Checkin();
        Assert.assertEquals(0,checkin.getColdFoodCount().size());
    }


}
