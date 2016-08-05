package music.analysis.feature.processor.factory;

import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.statistics.AveragePitchStatistic;
import music.analysis.feature.processor.statistics.AverageRhythmValueStatistic;
import music.analysis.feature.processor.statistics.PitchRangeStatistic;
import music.analysis.feature.processor.statistics.PitchStandardDeviationStatistic;
import music.analysis.feature.processor.statistics.PitchVarietyStatistic;
import music.analysis.feature.processor.statistics.RepeatedSoundRhythmPairStatistic;
import music.analysis.feature.processor.statistics.RhythmStandardDeviationStatistic;
import music.analysis.feature.processor.statistics.RhythmVarietyStatistic;
import music.analysis.feature.processor.statistics.RhythmicRangeStatistic;
import music.analysis.feature.processor.statistics.StrongBeatStatistic;
import music.analysis.feature.processor.statistics.density.ChordNotesDensityStatistic;
import music.analysis.feature.processor.statistics.density.NonScaleDensityStatistic;
import music.analysis.feature.processor.statistics.density.RestDensityStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourDirectionStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourStabilityStatistic;
import music.analysis.feature.processor.statistics.intervals.DiatonicStatistic;
import music.analysis.feature.processor.statistics.intervals.DissonanceStatistic;
import music.analysis.feature.processor.statistics.intervals.IllegalJumpStatistic;
import music.analysis.feature.processor.statistics.intervals.RepeatedIntervalStatistic;
import music.harmony.Chord;
import music.harmony.Harmony;

import java.util.List;

public class StatisticProcessorFactory {

    private static final int POSSIBLE_RHYTHM_VALUES = 16;

    public static DoubleFeatureCounter createStatistic(StatisticName statisticName, Harmony scale, List<Chord> chordList,
                                                       int numberOfMeasures) {
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
            case ILLEGAL_JUMP:
                return new IllegalJumpStatistic();
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
                return new RhythmVarietyStatistic();
            case CHORD_NOTES:
                return new ChordNotesDensityStatistic(chordList);
            case AVERAGE_PITCH:
                return new AveragePitchStatistic();
            case PITCH_STANDARD_DEVIATION:
                return new PitchStandardDeviationStatistic();
            case AVERAGE_RHYTHM:
                return new AverageRhythmValueStatistic();
            case RHYTHM_STANDARD_DEVIATION:
                return new RhythmStandardDeviationStatistic();
            case STRONG_BEAT:
                return new StrongBeatStatistic(numberOfMeasures);
            default:
                return null;
        }
    }
}
