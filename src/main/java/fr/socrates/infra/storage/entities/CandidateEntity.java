package fr.socrates.infra.storage.entities;

import fr.socrates.domain.candidate.Candidate;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="candidates_id", sequenceName="candidates_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;

    public CandidateEntity() {
    }

    public CandidateEntity(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidateEntity that = (CandidateEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Candidate toDomain() {
        return Candidate.withEmail(email);
    }

    public static CandidateEntity fromDomain(Candidate candidate) {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setEmail(candidate.getEmail().getEmail());
        return candidateEntity;
    }
}
