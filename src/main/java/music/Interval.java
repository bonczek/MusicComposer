package music;

public class Interval {

    private static final int PITCHES_IN_OCTAVE = 12;
    private final Sound firstNote;
    private final Sound nextNote;
    private PitchInterval pitchInterval;
    private int octaveDifference;

    public Interval(Sound firstNote, Sound nextNote) {
        this.firstNote = firstNote;
        this.nextNote = nextNote;
        calculateIntervalValues();
    }

    public Sound getFirstNote() {
        return firstNote;
    }

    public Sound getNextNote() {
        return nextNote;
    }

    public PitchInterval getPitchInterval() {
        return pitchInterval;
    }

    public void setPitchInterval(PitchInterval pitchInterval) {
        this.pitchInterval = pitchInterval;
    }

    public int getOctaveDifference() {
        return octaveDifference;
    }

    public void setOctaveDifference(int octaveDifference) {
        this.octaveDifference = octaveDifference;
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
        int pitchSemitonesInterval = semitonesDifference % PITCHES_IN_OCTAVE;
        this.octaveDifference = semitonesDifference / PITCHES_IN_OCTAVE;
        for (PitchInterval pitchInterval : PitchInterval.values()) {
            if (pitchInterval.semitones() == Math.abs(pitchSemitonesInterval)) {
                this.pitchInterval = pitchInterval;
                break;
            }
        }
    }
}
