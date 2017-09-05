package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConfirmationRepositoryAdaptor implements ConfirmationRepository {
    private JpaConfirmationRepository jpaConfirmationRepository;
    private JpaCandidateRepository jpaCandidateRepository;

    @Autowired
    public ConfirmationRepositoryAdaptor(JpaConfirmationRepository jpaConfirmationRepository, JpaCandidateRepository jpaCandidateRepository) {
        this.jpaConfirmationRepository = jpaConfirmationRepository;
        this.jpaCandidateRepository = jpaCandidateRepository;
    }

    @Override
    public void add(Confirmation confirmation) {
        CandidateEntity candidate = jpaCandidateRepository.findByCandidateId(confirmation.getCandidateId().getId());
        jpaConfirmationRepository.save(ConfirmationEntity.fromDomain(confirmation, candidate));
    }

    @Override
    public List<Confirmation> getConfirmations() {
        return jpaConfirmationRepository.findAll()
                .stream()
                .map(confirmationEntity -> createConfirmation(confirmationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public boolean confirmationExists(Candidate candidate) {
        final String candidateId = candidate.getCandidateId().getId();
        final ConfirmationEntity confirmation = jpaConfirmationRepository.findByCandidateCandidateId(candidateId);
        return confirmation != null;
    }

    private Confirmation createConfirmation(ConfirmationEntity confirmationEntity) {
        final CandidateId candidateId = new CandidateId(confirmationEntity.getCandidate().getCandidateId());
        final Date confirmationDate = confirmationEntity.getConfirmationDate();

        return new Confirmation(candidateId, convertConfirmationDate(confirmationDate));
    }

    private LocalDate convertConfirmationDate(Date confirmationDate) {
        return confirmationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
