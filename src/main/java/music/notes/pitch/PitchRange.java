package music.notes.pitch;

public class PitchRange {
    private final int lowestMidiValue;
    private final int highestMidiValue;

    public PitchRange(int lowestPitch, int highestMidiValue) {
        this.lowestMidiValue = lowestPitch;
        this.highestMidiValue = highestMidiValue;
    }

    public boolean outOfRange(int midiValue) {
        return midiValue > highestMidiValue || midiValue < lowestMidiValue;
    }

}
