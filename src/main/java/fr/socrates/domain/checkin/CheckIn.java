package fr.socrates.domain.checkin;

class CheckIn {
    public static final int COLD_FOOD_HOUR = 21;
    private ParticipantId participantId;

    public CheckIn(ParticipantId participantId) {
        this.participantId = participantId;
    }

    public ParticipantId getParticipantId() {
        return participantId;
    }
}
