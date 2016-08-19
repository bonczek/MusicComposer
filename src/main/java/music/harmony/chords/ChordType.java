package music.harmony.chords;

import music.harmony.HarmonyRelation;
import music.notes.pitch.PitchInterval;

public enum ChordType implements HarmonyRelation {
    MAJOR(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MAJOR_THIRD, PitchInterval.PERFECT_FIFTH}),
    MINOR(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MINOR_THIRD, PitchInterval.PERFECT_FIFTH});

    private PitchInterval[] intervals;

    ChordType(PitchInterval[] intervals) {
        this.intervals = intervals;
    }

    public PitchInterval[] getIntervals() {
        return intervals;
    }
}
