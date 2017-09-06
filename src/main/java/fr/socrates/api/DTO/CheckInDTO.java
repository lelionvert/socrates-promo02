package fr.socrates.api.DTO;

import java.time.LocalDateTime;

public class CheckInDTO {
    private String email;
    private LocalDateTime date;

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
