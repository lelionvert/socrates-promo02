package fr.socrates.infra.storage.repositories;

import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaConfirmationRepository extends JpaRepository<ConfirmationEntity, Long> {
    ConfirmationEntity findByCandidateCandidateId(String candidateId);
}
