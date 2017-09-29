package fr.socrates.api.controller;

import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateException;
import fr.socrates.domain.candidate.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Collection<CandidateDTO>> getCandidates() {
        return ResponseEntity.ok(CandidateDTO.domainToDTO(candidateService.getRegisteredCandidates()));
    }

    @GetMapping("/{id:.+}")
    public ResponseEntity getCandidate(@PathVariable String id) {
        Optional<Candidate> foundCandidate = candidateService.findCandidateByEmail(id);
        return foundCandidate.<ResponseEntity>map(candidate -> ResponseEntity.ok(CandidateDTO.domainToDTO(candidate))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        try {
            candidateService.add(CandidateDTO.DTOToDomain(candidateDTO));
            Optional<Candidate> foundCandidate = candidateService.findCandidateByEmail(candidateDTO.getEmail());
            return foundCandidate.<ResponseEntity>map(candidate -> ResponseEntity.ok(CandidateDTO.domainToDTO(candidate))).orElseGet(() -> ResponseEntity.noContent().build());
        } catch (CandidateException.CandidatePersisteDataException e) {
            return ResponseEntity.badRequest().build();
        } catch (CandidateException.CandidateExistingException e) {
            return ResponseEntity.status(409).build();
        }

    }
}
