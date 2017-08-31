package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        return null;
    }

    @Override
    public boolean confirmationExists(Candidate candidate) {
        return false;
    }
}
