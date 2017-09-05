package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.repositories.JpaCandidateRepository;
import fr.socrates.infra.storage.repositories.JpaConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@WebMvcTest(ConfirmationController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ConfirmationControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private ConfirmationService confirmationService;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void should_return_all_confirmations_as_json() throws Exception {
        Candidate candidateJohn = Candidate.singleRoomWithEmail("john@doe.fr");
        Confirmation confirmationJohn = new Confirmation(candidateJohn.getCandidateId(), LocalDate.now());

        given(confirmationService.getListConfirmations()).willReturn(Collections.singletonList(confirmationJohn));
        given(candidateService.findCandidateByCandidateID(any(CandidateId.class))).willReturn(Optional.of(candidateJohn));

        this.mvc.perform(get("/confirmations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("john@doe.fr")));
    }

    @Test
    public void should_confirm_one_candidate() throws Exception {
        given(confirmationService.confirm(any(String.class), any(LocalDate.class), any(Payment.class), any(AccommodationChoice.class))).willReturn(true);

        ConfirmationDTO confirmationDTO = ConfirmationDTO.mapToDTO("johndoe@dodo.fr", AccommodationChoice.SINGLE_ROOM, Payment.TRANSFER);
        this.mvc.perform(post("/confirmations")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(confirmationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("johndoe@dodo.fr")))
                .andExpect(jsonPath("$.accommodationChoice", is("SINGLE_ROOM")))
                .andExpect(jsonPath("$.payment", is("TRANSFER")));
    }

    @Test
    public void should__return_not_found_error() throws Exception {
        candidateService.add(Candidate.singleRoomWithEmail("john@doe.fr"));
        candidateService.add(Candidate.singleRoomWithEmail("johndoe@dodo.fr"));

        ConfirmationDTO confirmationDTOOnNonExistingCandidate = ConfirmationDTO.mapToDTO("johndoe@notExistingCandidate.fr", AccommodationChoice.SINGLE_ROOM, Payment.TRANSFER);
        this.mvc.perform(post("/confirmations")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(confirmationDTOOnNonExistingCandidate)))
                .andExpect(status().is(404));
    }
}
