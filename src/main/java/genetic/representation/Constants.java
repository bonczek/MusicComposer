package genetic.representation;

public enum Constants {
    //@todo change name and remove max midi value
    REST(-1), TENUTO(-2), MAX_MIDI_VALUE(127);

    private int value;

    Constants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
