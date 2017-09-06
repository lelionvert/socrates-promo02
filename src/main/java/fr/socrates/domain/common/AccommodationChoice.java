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
}
