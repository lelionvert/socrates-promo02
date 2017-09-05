package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class MealControllerTest {
    private MockMvc mvc;

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        checkInService.addNewCheckIn(new CheckIn(Candidate.singleRoomWithEmail("john@doe.fr").getCandidateId(), LocalDateTime.of(2017, 10, 9, 22, 00)));
        checkInService.addNewCheckIn(new CheckIn(Candidate.singleRoomWithEmail("john@pasdoe.fr").getCandidateId(), LocalDateTime.of(2017, 10, 9, 18, 00)));
    }

    @Ignore
    @Test
    public void should_return_number_of_cold_meal() throws Exception {
        this.mvc.perform(get("/meals/cold-meals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }
}
