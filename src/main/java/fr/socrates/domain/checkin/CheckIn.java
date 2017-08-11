package fr.socrates.domain.checkin;

class CheckIn {
    private final ParticipantId participantId;

    public CheckIn(ParticipantId participantId) {
        this.participantId = participantId;
    }

    public ParticipantId getParticipantId() {
        return participantId;
    }
}
