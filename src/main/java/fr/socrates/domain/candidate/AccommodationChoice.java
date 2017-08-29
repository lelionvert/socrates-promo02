package fr.socrates.domain.candidate;

public enum AccommodationChoice {
    SINGLE_ROOM("Single Room"), DOUBLE_ROOM("Double Room"), TRIPLE_ROOM("Triple Room"), NO_ACCOMMONDATION("No Accommondation");

    String accommodationChoiceValue;

    AccommodationChoice(String accommodationChoiceValue) {
        this.accommodationChoiceValue = accommodationChoiceValue;
    }

    @Override
    public String toString() {
        return "AccommodationChoice{" +
                "accommodationChoiceValue='" + accommodationChoiceValue + '\'' +
                '}';
    }
}
