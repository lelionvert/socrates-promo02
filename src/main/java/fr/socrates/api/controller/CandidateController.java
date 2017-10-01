package fr.socrates.api.controller;

import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateException;
import fr.socrates.domain.candidate.CandidateService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
@Api(description = "Operations over candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @ApiOperation(value = "View the list of every registered candidate", response = CandidateDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all registered candidates")
    })
    public ResponseEntity<Collection<CandidateDTO>> getCandidates() {
        return ResponseEntity.ok(CandidateDTO.domainToDTO(candidateService.getRegisteredCandidates()));
    }

    @GetMapping("/{id:.+}")
    @ApiOperation(value = "View particular candidate information", response = CandidateDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Display information about the candidate"),
            @ApiResponse(code = 204, message = "The corresponding candidate was not found")
    })
    public ResponseEntity getCandidate(@ApiParam(name = "id", value = "Id of the particular candidate", required = true) @PathVariable String id) {
        Optional<Candidate> foundCandidate = candidateService.findCandidateByEmail(id);
        return foundCandidate.<ResponseEntity>map(candidate -> ResponseEntity.ok(CandidateDTO.domainToDTO(candidate))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    @ApiOperation(value = "Allow to create a new candidate", response = CandidateDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the candidate"),
            @ApiResponse(code = 400, message = "Error while saving candidate in the database"),
            @ApiResponse(code = 409, message = "Candidate already existed in the database")
    })
    public ResponseEntity createCandidate(@ApiParam(required = true) @Valid @RequestBody CandidateDTO candidateDTO) {
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
