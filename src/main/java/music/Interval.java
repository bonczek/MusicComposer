package music;

public class Interval {

    private final Pitch firstNote;
    private final Pitch nextNote;
    private PitchInterval pitchInterval;
    private int octaveDifference;

    public Interval(Pitch firstNote, Pitch nextNote) {
        this.firstNote = firstNote;
        this.nextNote = nextNote;
        calculateIntervalValues();
    }

    public PitchInterval getPitchInterval() {
        return pitchInterval;
    }

    public int getOctaveDifference() {
        return octaveDifference;
    }

    public boolean moreThanOctave() {
        if (octaveDifference == 0) {
            return false;
        } else if ((octaveDifference == 1 || octaveDifference == -1) && pitchInterval.equals(PitchInterval.PERFECT_UNISON)) {
            return false;
        } else {
            return true;
        }
    }

    private void calculateIntervalValues() {
        int semitonesDifference = nextNote.getMidiValue() - firstNote.getMidiValue();
        int pitchSemitonesInterval = semitonesDifference % Pitch.NOTES_IN_OCTAVE;
        this.octaveDifference = semitonesDifference / Pitch.NOTES_IN_OCTAVE;
        for (PitchInterval pitchInterval : PitchInterval.values()) {
            if (pitchInterval.semitones() == Math.abs(pitchSemitonesInterval)) {
                this.pitchInterval = pitchInterval;
                break;
            }
        }
    }
}
