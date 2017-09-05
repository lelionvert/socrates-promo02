package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorService;
import org.hamcrest.Matchers;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class SponsorControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SponsorService sponsorService;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void should_list_no_sponsor_when_no_sponsor_has_been_added() throws Exception {
        this.mvc.perform(get("/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void should_list_one_sponsor_when_getting_all_sponsors_and_there_is_only_one_sponsor() throws Exception {
        Sponsor sponsor = new Sponsor.SponsorBuilder()
                .withSIREN("513205344")
                .withSIRET("51320534400031")
                .withName("Arolla")
                .withContractRepresentative("arolla@arolla.fr")
                .withContact("arolla@arolla.fr")
                .withAmountOfSponsoring(100)
                .createSponsor();
        sponsorService.addSponsor(sponsor);

        this.mvc.perform(get("/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("513205344")))
                .andExpect(jsonPath("$[0].name", is("Arolla")))
                .andExpect(jsonPath("$[0].siret", is("51320534400031")))
                .andExpect(jsonPath("$[0].siren", is("513205344")))
                .andExpect(jsonPath("$[0].contractRepresentative", is("arolla@arolla.fr")))
                .andExpect(jsonPath("$[0].contact", is("arolla@arolla.fr")))
                .andExpect(jsonPath("$[0].amount", is(100d)));
    }
}
