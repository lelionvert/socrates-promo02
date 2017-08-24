package fr.socrates.api.controller;

import fr.socrates.api.DTO.CandidateDTO;
import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.attendee.Accommodation;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ConfirmationController {
    private ConfirmationService confirmationService;

    public ConfirmationController(ConfirmationService confirmationService) {
        this.confirmationService = confirmationService;
    }

    @GetMapping
    public ResponseEntity<Collection<ConfirmationDTO>> getConfirmations() {
        return ResponseEntity.ok(ConfirmationDTO.domainToDTO(confirmationService.getListAttendee()));
    }

    @PostMapping
    public ResponseEntity confirmCandidate(@RequestBody CandidateDTO candidateDTO) {
        boolean confirm = confirmationService.confirm(candidateDTO.getEmail());
        if (confirm) {
            return ResponseEntity.ok(ConfirmationDTO.domainToDTO(confirmationService.getListAttendee()));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
