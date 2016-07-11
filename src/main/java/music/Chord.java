package music;

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
}
