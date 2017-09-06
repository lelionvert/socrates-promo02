package fr.socrates.api.controller;

import fr.socrates.api.DTO.SponsorDTO;
import fr.socrates.domain.sponsor.SponsorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sponsors")
public class SponsorController {
    private SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public ResponseEntity getSponsors() {
        List<SponsorDTO> sponsorList = sponsorService.getSponsorsList()
                .stream()
                .map(SponsorDTO::domainToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sponsorList);
    }

    @PostMapping
    public ResponseEntity addSponsor(@Valid @RequestBody SponsorDTO sponsorDTO) {
        try {
            sponsorService.addSponsor(SponsorDTO.dTOtoDomain(sponsorDTO));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIOException(MethodArgumentNotValidException e) {
        return e.getMessage();
    }
}
