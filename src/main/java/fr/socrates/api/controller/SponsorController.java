package fr.socrates.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/sponsors")
public class SponsorController {
    @GetMapping
    public ResponseEntity getSponsors() {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
