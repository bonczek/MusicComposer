package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

//@todo not used statistic
public class SemitonesDifferenceStatistic extends IntervalStatistic {

    private static final int MAX_ACCEPTABLE_INTERVALS_RANGE = 24;

    @Override
    protected void processInterval(Interval interval) {
        numerator += Math.abs(interval.getSemitonesDifference());
        denominator += MAX_ACCEPTABLE_INTERVALS_RANGE;
    }
}
