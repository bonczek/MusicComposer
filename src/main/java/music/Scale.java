package music;

public interface Scale {
    PitchInterval[] MAJOR_SCALE = new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval.MAJOR_SECOND,
            PitchInterval.MAJOR_THIRD, PitchInterval.PERFECT_FOURTH, PitchInterval.PERFECT_FIFTH, PitchInterval
            .MAJOR_SIXTH, PitchInterval.MAJOR_SEVENTH};
    PitchInterval[] MINOR_PENTATONIC_SCALE = new PitchInterval[]{PitchInterval.PERFECT_UNISON, PitchInterval
            .MINOR_THIRD, PitchInterval.PERFECT_FOURTH, PitchInterval.PERFECT_FIFTH, PitchInterval.MINOR_SEVENTH};
}
