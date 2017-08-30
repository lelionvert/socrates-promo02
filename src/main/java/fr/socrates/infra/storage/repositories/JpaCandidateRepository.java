package fr.socrates.infra.storage.repositories;

import fr.socrates.infra.storage.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {
    CandidateEntity findByEmail(String email);
    CandidateEntity findByCandidateId(String candidateId);
}
