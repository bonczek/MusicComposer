package music;

import music.notes.pitch.PitchInterval;

public enum ChordName implements HarmonyRelation {
    MAJOR(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MAJOR_THIRD, PitchInterval.PERFECT_FIFTH}),
    MINOR(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MINOR_THIRD, PitchInterval.PERFECT_FIFTH});

    private PitchInterval[] intervals;

    ChordName(PitchInterval[] intervals) {
        this.intervals = intervals;
    }

    public PitchInterval[] getIntervals() {
        return intervals;
    }
}
