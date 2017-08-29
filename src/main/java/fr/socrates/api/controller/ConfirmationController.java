package fr.socrates.api.controller;

import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.common.AccommodationChoice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/confirmations")
public class ConfirmationController {
    private ConfirmationService confirmationService;
    private CandidateService candidateService;

    public ConfirmationController(ConfirmationService confirmationService, CandidateService candidateService) {
        this.confirmationService = confirmationService;
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Collection<ConfirmationDTO>> getConfirmations() {
        ArrayList<ConfirmationDTO> confirmationDTOS = new ArrayList<>();
        confirmationService.getConfirmations().forEach(confirmation -> {
            Optional<Candidate> candidateByCandidateID = candidateService.findCandidateByCandidateID(confirmation.getCandidateId());
            candidateByCandidateID.ifPresent(candidate -> confirmationDTOS.add(ConfirmationDTO.mapToDTO(candidate.getEmail().getEmail(), confirmation.getAccommodationChoice(), confirmation.getPayment())));
        } );
        return ResponseEntity.ok(confirmationDTOS);
    }

    @PostMapping
    public ResponseEntity confirmCandidate(@RequestBody ConfirmationDTO confirmationDTO) {

        boolean confirm = confirmationService.confirm(confirmationDTO.getEmail(), LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);

        if (confirm) {
            return ResponseEntity.ok(ConfirmationDTO.mapToDTO(confirmationDTO.getEmail(), confirmationDTO.getAccommodationChoice(), confirmationDTO.getPayment()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
