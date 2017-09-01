package fr.socrates.infra.storage.entities;

import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.candidate.Candidate;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "confirmations")
public class ConfirmationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmations_id")
    @SequenceGenerator(name="confirmations_id", sequenceName="confirmations_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private CandidateEntity candidate;
    @Column(name = "confirmation_date")
    private Date confirmationDate;

    public ConfirmationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfirmationEntity that = (ConfirmationEntity) o;

        if (candidate != null ? !candidate.equals(that.candidate) : that.candidate != null) return false;
        return confirmationDate != null ? confirmationDate.equals(that.confirmationDate) : that.confirmationDate == null;
    }

    @Override
    public int hashCode() {
        int result = candidate != null ? candidate.hashCode() : 0;
        result = 31 * result + (confirmationDate != null ? confirmationDate.hashCode() : 0);
        return result;
    }

    public static ConfirmationEntity fromDomain(Confirmation confirmation, CandidateEntity candidate) {
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(Date.from(confirmation.getConfirmationDate().atZone(ZoneId.systemDefault()).toInstant()));
        return confirmationEntity;
    }
}
