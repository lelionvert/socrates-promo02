package fr.socrates.api.controller;

import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.common.AccommodationChoice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@Api(description = "Operations over confirmations")
@RequestMapping("/confirmations")
public class ConfirmationController {
    private final ConfirmationService confirmationService;
    private final CandidateService candidateService;

    public ConfirmationController(ConfirmationService confirmationService, CandidateService candidateService) {
        this.confirmationService = confirmationService;
        this.candidateService = candidateService;
    }

    @GetMapping
    @ApiOperation(value = "List of all registered confirmations", response = ConfirmationDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all registered confirmations")
    })
    public ResponseEntity<Collection<ConfirmationDTO>> getConfirmations() {
        ArrayList<ConfirmationDTO> confirmationDTOS = new ArrayList<>();
        confirmationService.getConfirmations().forEach(confirmation -> {
            Optional<Candidate> candidateByCandidateID = candidateService.findCandidateByCandidateID(confirmation.getCandidateId());
            candidateByCandidateID.ifPresent(candidate -> confirmationDTOS.add(ConfirmationDTO.domainToDTO(candidate)));
        } );
        return ResponseEntity.ok(confirmationDTOS);
    }

    @PostMapping
    @ApiOperation(value = "Allow to confirm a candidate", response = ConfirmationDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully confirmed the candidate"),
            @ApiResponse(code = 404, message = "Candidate was not found in the database")
    })
    public ResponseEntity confirmCandidate(@RequestBody ConfirmationDTO confirmationDTO) {

        boolean confirm = confirmationService.confirm(confirmationDTO.getEmail(), LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);

        if (confirm) {
            return candidateService.findCandidateByEmail(confirmationDTO.getEmail())
                    .map(ConfirmationDTO::domainToDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
