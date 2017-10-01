package fr.socrates.api.controller;

import fr.socrates.api.DTO.SponsorDTO;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sponsors")
@Api(description = "Operations over sponsors")
public class SponsorController {
    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    @ApiOperation(value = "List of all registered sponsors", response = SponsorDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all registered sponsors")
    })
    public ResponseEntity getSponsors() {
        List<SponsorDTO> sponsorList = sponsorService.getSponsorsList()
                .stream()
                .map(SponsorDTO::domainToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sponsorList);
    }

    @PostMapping
    @ApiOperation(value = "Add a sponsor", response = SponsorDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sponsor successfully added"),
            @ApiResponse(code = 400, message = "Sponsor already exists in the database")
    })
    public ResponseEntity addSponsor(@Valid @RequestBody SponsorDTO sponsorDTO) {
        Optional<Sponsor> createdSponsor = sponsorService.addSponsor(SponsorDTO.dTOtoDomain(sponsorDTO));
        return createdSponsor.<ResponseEntity>map(sponsor -> ResponseEntity.ok(SponsorDTO.domainToDTO(sponsor))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIOException(MethodArgumentNotValidException e) {
        return e.getMessage();
    }
}
