package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.repositories.JpaCandidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CandidateControllerTest {
    private MockMvc mvc;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JpaCandidateRepository jpaCandidateRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        candidateService.add(Candidate.singleRoomWithEmail("john@doe.fr"));

        given(jpaCandidateRepository.findByEmail("john@doe.fr")).willReturn(new CandidateEntity(1L, "john@doe.fr"));
    }

    @Test
    public void should_return_all_candidates_as_json() throws Exception {
        this.mvc.perform(get("/candidates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("john@doe.fr")));
    }

    @Test
    public void should_return_one_candidate_as_json() throws Exception {
        this.mvc.perform(get("/candidates/john@doe.fr"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("john@doe.fr")));
    }

    @Test
    public void should_add_one_candidate_to_repository() throws Exception {
        CandidateDTO candidateDTO = CandidateDTO.domainToDTO(Candidate.singleRoomWithEmail("test@test.fr"));
        this.mvc.perform(post("/candidates")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(candidateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@test.fr")));
    }
}
