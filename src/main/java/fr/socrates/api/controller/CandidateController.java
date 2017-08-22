package fr.socrates.api.controller;

import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Collection<CandidateDTO>> getCandidates(){
        Collection<CandidateDTO> candidates = CandidateDTO
            .domainToDTO(candidateService.getRegisteredCandidates());
        return ResponseEntity.ok(candidates);
    }

    @PostMapping
    public ResponseEntity createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        candidateService.add(CandidateDTO.DTOToDomain(candidateDTO));

        Optional<Candidate> candidate = candidateService.findCandidateByEmail(candidateDTO.getEmail().getEmail());
        if (candidate.isPresent())
            return ResponseEntity.ok(CandidateDTO.domainToDTO(candidate.get()));
        else
            return ResponseEntity.badRequest().build();
    }
}
