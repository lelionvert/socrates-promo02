package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.common.AccommodationChoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConfirmationControllerTest {
    private MockMvc mvc;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ConfirmationService confirmationService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void should_return_all_confirmations_as_json() throws Exception {
        candidateService.add(Candidate.singleRoomWithEmail("john@doe.fr"));
        candidateService.add(Candidate.singleRoomWithEmail("johndoe@dodo.fr"));
        confirmationService.confirm("john@doe.fr", LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);

        this.mvc.perform(get("/confirmations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("john@doe.fr")));
    }

    @Test
    public void should_confirm_one_candidate() throws Exception {
        final Candidate candidate = Candidate.singleRoomWithEmail("john@doe.fr");
        candidateService.add(candidate);
        candidateService.add(Candidate.singleRoomWithEmail("johndoe@dodo.fr"));

        ConfirmationDTO confirmationDTO = ConfirmationDTO.domainToDTO(candidate);
        this.mvc.perform(post("/confirmations")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(confirmationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("john@doe.fr")))
                .andExpect(jsonPath("$.accommodationChoice", is("SINGLE_ROOM")))
                .andExpect(jsonPath("$.payment", is("TRANSFER")));
    }

    @Test
    public void should_return_not_found_error() throws Exception {
        candidateService.add(Candidate.singleRoomWithEmail("john@doe.fr"));
        candidateService.add(Candidate.singleRoomWithEmail("johndoe@dodo.fr"));

        ConfirmationDTO confirmationDTOOnNonExistingCandidate = ConfirmationDTO.domainToDTO(Candidate.singleRoomWithEmail("johndoe@notExistingCandidate.fr"));
        this.mvc.perform(post("/confirmations")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(confirmationDTOOnNonExistingCandidate)))
                .andExpect(status().is(404));
    }


}
