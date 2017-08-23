package fr.socrates.api.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import java.nio.charset.Charset;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class CandidateControllerTest {
    private MockMvc mvc;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private WebApplicationContext context;


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();
    }

    @Test
    public void should_return_one_candidate_as_json() throws Exception {
        candidateService.add(Candidate.withEmail("john@doe.fr"));
        this.mvc.perform(get("/candidates"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].email.email", is("john@doe.fr")));
    }

    @Test
    public void should_add_one_candidate_to_repository() throws Exception{
        CandidateDTO candidateDTO = CandidateDTO.domainToDTO(Candidate.withEmail("john@doe.fr"));
        this.mvc.perform(post("/candidates")
            .contentType(APPLICATION_JSON_UTF8)
            .content(convertObjectToJsonBytes(candidateDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email.email", is("john@doe.fr")));
    }
}
