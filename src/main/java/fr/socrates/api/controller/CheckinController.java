package fr.socrates.api.controller;

import fr.socrates.api.DTO.CheckInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

    @GetMapping
    public ResponseEntity getCheckins(@RequestBody CheckInDTO checkinDTO) {
        return ResponseEntity.ok().build();
    }
}
