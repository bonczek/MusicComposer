package music.harmony;

import music.notes.pitch.PitchInterval;

public enum ScaleName implements HarmonyRelation {
    MAJOR_SCALE(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MAJOR_SECOND,
            PitchInterval.MAJOR_THIRD, PitchInterval.PERFECT_FOURTH, PitchInterval.PERFECT_FIFTH,
            PitchInterval.MAJOR_SIXTH, PitchInterval.MAJOR_SEVENTH}),
    MINOR_PENTATONIC_SCALE(new PitchInterval[]{PitchInterval.PERFECT_UNISON,
            PitchInterval.MINOR_THIRD, PitchInterval.PERFECT_FOURTH,
            PitchInterval.PERFECT_FIFTH, PitchInterval.MINOR_SEVENTH});

    private PitchInterval[] intervals;

    ScaleName(PitchInterval[] intervals) {
        this.intervals = intervals;
    }

    public PitchInterval[] getIntervals() {
        return intervals;
    }
}