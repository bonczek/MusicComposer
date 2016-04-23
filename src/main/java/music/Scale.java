package music;

import music.notes.pitch.PitchInterval;

public enum Scale {
    MAJOR_SCALE(new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MAJOR_SECOND,
            PitchInterval.MAJOR_THIRD, PitchInterval.PERFECT_FOURTH, PitchInterval.PERFECT_FIFTH,
            PitchInterval.MAJOR_SIXTH, PitchInterval.MAJOR_SEVENTH}),
    MINOR_PENTATONIC_SCALE(new PitchInterval[]{PitchInterval.PERFECT_UNISON,
            PitchInterval.MINOR_THIRD, PitchInterval.PERFECT_FOURTH,
            PitchInterval.PERFECT_FIFTH, PitchInterval.MINOR_SEVENTH});

    private PitchInterval[] intervals;

    Scale(PitchInterval[] intervals) {
        this.intervals = intervals;
    }

    public PitchInterval[] intervals() {
        return intervals;
    }
}
