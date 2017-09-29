package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.SponsorDTO;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SponsorControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SponsorService sponsorService;
    private Sponsor sponsor;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        sponsor = new Sponsor.SponsorBuilder()
                .withSIREN("513205344")
                .withSIRET("51320534400031")
                .withName("Arolla")
                .withContractRepresentative("arolla@arolla.fr")
                .withContact("arolla@arolla.fr")
                .withAmountOfSponsoring(100)
                .createSponsor();
    }

    @Test
    public void should_list_no_sponsor_when_no_sponsor_has_been_added() throws Exception {
        this.mvc.perform(get("/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void should_list_one_sponsor_when_getting_all_sponsors_and_there_is_only_one_sponsor() throws Exception {
        sponsorService.addSponsor(sponsor);

        this.mvc.perform(get("/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Arolla")))
                .andExpect(jsonPath("$[0].siren", is("513205344")))
                .andExpect(jsonPath("$[0].contractRepresentative", is("arolla@arolla.fr")))
                .andExpect(jsonPath("$[0].contact", is("arolla@arolla.fr")))
                .andExpect(jsonPath("$[0].amount", is(100d)));
    }

    @Test
    public void should_add_one_sponsor_to_repository() throws Exception {
        SponsorDTO sponsorDTO = SponsorDTO.domainToDTO(sponsor);
        this.mvc.perform(post("/sponsors")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(sponsorDTO)))
                .andExpect(status().isOk());
        assertThat(sponsorService.getSponsorsList()).hasSize(1);
    }

    @Test
    public void should_send_bad_request_when_siren_is_invalid() throws Exception {
        SponsorDTO sponsorDTO = SponsorDTO.domainToDTO(sponsor);
        sponsorDTO.setSiren("123");
        this.mvc.perform(post("/sponsors/")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(sponsorDTO)))
                .andExpect(status().is4xxClientError());
    }
}
