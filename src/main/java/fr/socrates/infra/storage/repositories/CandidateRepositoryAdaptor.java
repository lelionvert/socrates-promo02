package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.*;
import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;
import fr.socrates.domain.candidate.exceptions.UpdateCandidateNoNewDefinitionException;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.infra.storage.entities.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CandidateRepositoryAdaptor implements CandidateRepository {
    private JpaCandidateRepository jpaCandidateRepository;

    @Autowired
    public CandidateRepositoryAdaptor(JpaCandidateRepository jpaCandidateRepository) {
        this.jpaCandidateRepository = jpaCandidateRepository;
    }

    @Override
    public List<Candidate> findAll() {
        return jpaCandidateRepository.findAll()
                .stream()
                .map(CandidateEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Candidate findCandidateByEmail(String email) throws UnknownCandidateException {
        CandidateEntity candidateEntity = jpaCandidateRepository.findByEmail(email);
        if (candidateEntity == null)
            throw new UnknownCandidateException();
        return candidateEntity.toDomain();
    }

    @Override
    public Candidate findCandidateById(CandidateId candidateId) throws UnknownCandidateException {
        CandidateEntity candidateEntity = jpaCandidateRepository.findByCandidateId(candidateId.getId());
        if (candidateEntity == null)
            throw new UnknownCandidateException();
        return candidateEntity.toDomain();
    }

    @Override
    public Candidate addCandidate(Candidate candidate) throws AddCandidateException {
        if (candidate == null)
            throw new AddCandidateException();
        return jpaCandidateRepository.save(CandidateEntity.fromDomain(candidate)).toDomain();
    }

    @Override
    public Candidate updateCandidate(Candidate updateOfCandidate, String oldEmail) throws CandidateException {
        if (updateOfCandidate == null)
            throw new UpdateCandidateNoNewDefinitionException();
        if (oldEmail == null || oldEmail.isEmpty())
            throw new UnknownCandidateException();

        CandidateEntity candidateToUpdate = jpaCandidateRepository.findByEmail(oldEmail);
        if (candidateToUpdate == null)
            throw new UnknownCandidateException();
        candidateToUpdate.update(updateOfCandidate);
        return jpaCandidateRepository.save(candidateToUpdate).toDomain();
    }

    @Override
    public void updateContactInfos(CandidateId candidateId, ContactInformation contactInformation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAccommodationChoices(CandidateId candidateId, AccommodationChoices accommodationChoices) {
        throw new UnsupportedOperationException();
    }
}
