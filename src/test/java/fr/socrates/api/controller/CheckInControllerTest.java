package fr.socrates.api.controller;

import fr.socrates.SocratesApplication;
import fr.socrates.api.DTO.CheckInDTO;
import fr.socrates.domain.checkin.CheckInRepository;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class CheckInControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CheckInRepository checkInRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void should_add_one_checkin_to_repository() throws Exception {
        CheckInDTO checkInDTO = new CheckInDTO();
        checkInDTO.setEmail("john@doe.fr");
        checkInDTO.setDate(LocalDateTime.of(2017, 8, 30, 21, 15));
        this.mvc.perform(post("/checkins")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(checkInDTO)))
                .andExpect(status().isOk());
        assertThat(checkInRepository.getCheckIns()).hasSize(1);
    }
}
