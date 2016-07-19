package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

public class OctaveDifferenceStatistic extends IntervalStatistic {

    private static final int MAX_OCTAVE_DIFF = 4;

    @Override
    protected void processInterval(Interval interval) {
        numerator += Math.abs(interval.getOctaveDifference());
        denominator += MAX_OCTAVE_DIFF;
    }
}
