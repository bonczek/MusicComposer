package music.notes.pitch;

public class Interval {

    private final Pitch firstNote;
    private final Pitch nextNote;
    private PitchInterval pitchInterval;
    private int octaveDifference;
    private int semitonesDifference;
    private IntervalDirection direction;
    public Interval(Pitch firstNote, Pitch nextNote) {
        this.firstNote = firstNote;
        this.nextNote = nextNote;
        calculateIntervalValues();
    }

    public int getSemitonesDifference() {
        return semitonesDifference;
    }

    public Pitch getFirstNote() {
        return firstNote;
    }

    public Pitch getNextNote() {
        return nextNote;
    }

    public IntervalDirection getDirection() {
        return direction;
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
        } else
            return !((octaveDifference == 1 || octaveDifference == -1) && pitchInterval.equals(PitchInterval.PERFECT_UNISON));
    }

    public boolean perfectConsonance() {
        return pitchInterval.equals(PitchInterval.PERFECT_UNISON) || pitchInterval.equals
                (PitchInterval.PERFECT_FIFTH) || pitchInterval.equals(PitchInterval.PERFECT_FOURTH);
    }

    public boolean imperfectConsonance() {
        return pitchInterval.equals(PitchInterval.MAJOR_THIRD) || pitchInterval.equals
                (PitchInterval.MINOR_THIRD) || pitchInterval.equals(PitchInterval.MAJOR_SIXTH) || pitchInterval
                .equals(PitchInterval.MINOR_SIXTH);

    }

    public boolean dissonance() {
        return
//                pitchInterval.equals(PitchInterval.MAJOR_SECOND) ||
//                        pitchInterval.equals(PitchInterval.MINOR_SECOND) ||
                        pitchInterval.equals(PitchInterval.MINOR_SEVENTH) ||
                        pitchInterval.equals(PitchInterval.MAJOR_SEVENTH) ||
                        pitchInterval.equals(PitchInterval.TRITONE);
    }

    private void calculateIntervalValues() {
        this.semitonesDifference = nextNote.getMidiValue() - firstNote.getMidiValue();
        int pitchSemitonesInterval = semitonesDifference % Pitch.NOTES_IN_OCTAVE;
        this.octaveDifference = semitonesDifference / Pitch.NOTES_IN_OCTAVE;
        for (PitchInterval pitchInterval : PitchInterval.values()) {
            if (pitchInterval.semitones() == Math.abs(pitchSemitonesInterval)) {
                this.pitchInterval = pitchInterval;
                break;
            }
        }
        if (semitonesDifference > 0) {
            this.direction = IntervalDirection.UP;
        } else if (semitonesDifference == 0) {
            this.direction = IntervalDirection.STEADY;
        } else {
            this.direction = IntervalDirection.DOWN;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (octaveDifference != interval.octaveDifference) return false;
        if (!firstNote.equals(interval.firstNote)) return false;
        if (!nextNote.equals(interval.nextNote)) return false;
        if (pitchInterval != interval.pitchInterval) return false;
        return direction == interval.direction;

    }

    @Override
    public int hashCode() {
        int result = firstNote.hashCode();
        result = 31 * result + nextNote.hashCode();
        result = 31 * result + pitchInterval.hashCode();
        result = 31 * result + octaveDifference;
        result = 31 * result + direction.hashCode();
        return result;
    }
}
