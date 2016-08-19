package music.harmony.chords;

import music.harmony.Harmony;
import music.notes.pitch.Pitch;

public class Chord {

    private final Harmony chordHarmony;
    private final double startTime;
    private final double endTime;
    private final double durationTime;
    public Chord(Harmony chordHarmony, double startTime, double durationTime) {
        this.chordHarmony = chordHarmony;
        this.startTime = startTime;
        this.durationTime = durationTime;
        this.endTime = startTime + durationTime;
    }

    public Harmony getChordHarmony() {
        return chordHarmony;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getDurationTime() {
        return durationTime;
    }

    public boolean fitHarmony(Pitch pitch) {
        return chordHarmony.fit(pitch);
    }

    public boolean finishedEarlierThan(double time) {
        return (Double.compare(endTime, time) <= 0);
    }
}
