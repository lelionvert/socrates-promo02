package fr.socrates.domain.common;

public enum AccommodationChoice {
    SINGLE_ROOM("Single Room"),
    DOUBLE_ROOM("Double Room"),
    TRIPLE_ROOM("Triple Room"),
    NO_ACCOMMODATION("No Accommondation");

    String accommodationChoiceValue;

    AccommodationChoice(String accommodationChoiceValue) {
        this.accommodationChoiceValue = accommodationChoiceValue;
    }

    public String getAccommodationChoiceValue() {
        return accommodationChoiceValue;
    }

    @Override
    public String toString() {
        return "AccommodationChoice{" +
                "accommodationChoiceValue='" + accommodationChoiceValue + '\'' +
                '}';
    }
}
