package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.EMail;

public class EmailDTO {

    private String email;

    public EmailDTO(String email) {
        this.email = email;
    }

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static EmailDTO domainToDTO(EMail eMail){
        return new EmailDTO(eMail.getEmail());
    }
}
