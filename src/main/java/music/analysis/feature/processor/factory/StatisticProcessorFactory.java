package music.analysis.feature.processor.factory;

import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.statistics.MusicalStatistic;
import music.analysis.feature.processor.statistics.PitchRangeStatistic;
import music.analysis.feature.processor.statistics.PitchVarietyStatistic;
import music.analysis.feature.processor.statistics.RepeatedSoundRhythmPairStatistic;
import music.analysis.feature.processor.statistics.RhythmVarietyStatistic;
import music.analysis.feature.processor.statistics.RhythmicRangeStatistic;
import music.analysis.feature.processor.statistics.density.NonScaleDensityStatistic;
import music.analysis.feature.processor.statistics.density.RestDensityStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourDirectionStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourStabilityStatistic;
import music.analysis.feature.processor.statistics.intervals.DiatonicStatistic;
import music.analysis.feature.processor.statistics.intervals.DissonanceStatistic;
import music.analysis.feature.processor.statistics.intervals.OctaveDifferenceStatistic;
import music.analysis.feature.processor.statistics.intervals.RepeatedIntervalStatistic;
import music.analysis.feature.processor.statistics.intervals.SemitonesDifferenceStatistic;
import music.harmony.Harmony;

public class StatisticProcessorFactory {

    private static final int POSSIBLE_RHYTHM_VALUES = 16;

    public static MusicalStatistic createStatistic(StatisticName statisticName, Harmony scale) {
        switch (statisticName) {
            case CONTOUR_DIRECTION:
                return new ContourDirectionStatistic();
            case CONTOUR_STABILITY:
                return new ContourStabilityStatistic();
            case DIATONIC_RATING:
                return new DiatonicStatistic();
            case DISSONANCE_RATING:
                return new DissonanceStatistic();
            case NON_SCALE_RATING:
                return new NonScaleDensityStatistic(scale);
            case OCTAVE_DIFFERENCE:
                return new OctaveDifferenceStatistic();
            case PITCH_RANGE:
                return new PitchRangeStatistic();
            case PITCH_VARIETY:
                return new PitchVarietyStatistic();
            case REPEATED_INTERVALS:
                return new RepeatedIntervalStatistic();
            case REPEATED_RHYTHM_INTERVALS:
                return new RepeatedSoundRhythmPairStatistic();
            case REST_DENSITY:
                return new RestDensityStatistic();
            case RHYTHM_RANGE:
                return new RhythmicRangeStatistic(POSSIBLE_RHYTHM_VALUES);
            case RHYTHM_VARIETY:
                return new RhythmVarietyStatistic(POSSIBLE_RHYTHM_VALUES);
            case SEMITONES_DIFFERENCE:
                return new SemitonesDifferenceStatistic();
            default:
                return null;
        }
    }
}
