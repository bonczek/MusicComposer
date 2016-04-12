package music;

public class Interval {

    private PitchInterval pitchInterval;

    private int octaveDifference;

    public Interval(int semitonesDifference) {
        int interval = semitonesDifference % 12;
        this.octaveDifference = semitonesDifference / 12;
        for (PitchInterval pitchInterval : PitchInterval.values()) {
            if (pitchInterval.semitones() == Math.abs(interval)) {
                this.pitchInterval = pitchInterval;
                break;
            }
        }
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
}
