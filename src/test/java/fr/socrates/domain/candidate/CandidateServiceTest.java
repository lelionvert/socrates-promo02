package fr.socrates.domain.candidate;

import fr.socrates.common.FakePrinter;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateServiceTest {

    private CandidateService candidateService;
    private FakePrinter printer;

    @Before
    public void setUp() throws Exception {
        printer = new FakePrinter();
        candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository(), printer);
    }

    @Test
    public void should_have_no_candidates_at_the_beginning() {
        assertThat(candidateService.hasCandidates()).isFalse();
    }

    @Test
    public void should_have_candidates_when_adding_candidates() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));

        assertThat(candidateService.hasCandidates()).isTrue();
    }

    @Test
    public void should_have_one_candidate_when_adding_one_candidate() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    @Test
    public void should_contains_the_candidate_I_have_added() {
        final String email = "test@test.net";
        candidateService.add(Candidate.withEmail(email));
        assertThat(candidateService.contains(Candidate.withEmail(email))).isTrue();
    }

    @Test
    public void should_guaranty_unicity_of_candidates() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        candidateService.add(Candidate.withEmail("test@test.net"));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    @Test
    public void should_print_no_candidates_at_the_beginning() throws Exception {
        candidateService.print();
        assertThat(printer.flush()).isEqualTo("No email to print");
    }

    @Test
    public void should_print_one_email_when_adding_one_candidate() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        candidateService.print();
        assertThat(printer.flush()).isEqualTo("test@test.net");
    }

    @Test
    public void should_print_two_emails_when_adding_two_candidates() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        candidateService.add(Candidate.withEmail("test2@test.net"));
        candidateService.print();
        assertThat(printer.flush()).isEqualTo("test@test.net; test2@test.net");
    }

}
