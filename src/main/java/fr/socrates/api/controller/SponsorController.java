package fr.socrates.api.controller;

import fr.socrates.api.DTO.SponsorDTO;
import fr.socrates.domain.sponsor.SponsorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
}
