package music.notes.pitch;

public class PitchRange {
    private final int lowestMidiValue;
    private final int highestMidiValue;

    public PitchRange(int lowestPitch, int highestMidiValue) {
        this.lowestMidiValue = lowestPitch;
        this.highestMidiValue = highestMidiValue;
    }

    public int getHighestMidiValue() {
        return highestMidiValue;
    }

    public int getLowestMidiValue() {
        return lowestMidiValue;
    }

    public boolean outOfRange(int midiValue) {
        return midiValue > highestMidiValue || midiValue < lowestMidiValue;
    }

}
