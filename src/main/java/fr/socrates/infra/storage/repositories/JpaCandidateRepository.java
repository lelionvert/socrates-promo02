package fr.socrates.infra.storage.repositories;

import fr.socrates.infra.storage.entities.CandidateEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaCandidateRepository extends CrudRepository<CandidateEntity, Long> {
    CandidateEntity findByEmail(String email);
}
