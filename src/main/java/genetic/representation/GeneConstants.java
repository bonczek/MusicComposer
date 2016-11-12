package genetic.representation;

public enum GeneConstants {
    REST(-1), TENUTO(-2), MAX_MIDI_VALUE(127);

    private int value;

    GeneConstants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
