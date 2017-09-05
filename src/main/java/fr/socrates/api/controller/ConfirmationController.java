package fr.socrates.api.controller;

import fr.socrates.api.DTO.ConfirmationDTO;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.common.AccommodationChoice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/confirmations")
public class ConfirmationController {
    private ConfirmationService confirmationService;
    private CandidateService candidateService;

    public ConfirmationController(ConfirmationService confirmationService, CandidateService candidateService) {
        this.confirmationService = confirmationService;
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Collection<ConfirmationDTO>> getConfirmations() throws CandidateException {
        ArrayList<ConfirmationDTO> confirmationDTOS = new ArrayList<>();
        List<Confirmation> listConfirmations = confirmationService.getListConfirmations();
        listConfirmations.forEach(confirmation ->{
            Optional<Candidate> candidateByCandidateID = null;
            try {
                candidateByCandidateID = candidateService.findCandidateByCandidateID(confirmation.getCandidateId());
            } catch (CandidateException e) {
                e.printStackTrace();
            }
            candidateByCandidateID.ifPresent(candidate -> confirmationDTOS.add(ConfirmationDTO.mapToDTO(candidate.getEmail(), confirmation.getAccommodationChoice(), confirmation.getPayment())));
        } );
        return ResponseEntity.ok(confirmationDTOS);
    }

    @PostMapping
    public ResponseEntity confirmCandidate(@RequestBody ConfirmationDTO confirmationDTO) throws CandidateException {

        boolean confirm = confirmationService.confirm(confirmationDTO.getEmail(), LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);

        if (confirm) {
            return ResponseEntity.ok(ConfirmationDTO.mapToDTO(confirmationDTO.getEmail(), confirmationDTO.getAccommodationChoice(), confirmationDTO.getPayment()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
