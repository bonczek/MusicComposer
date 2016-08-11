package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

/**
 * Analyze diatonic intervals in melody.
 * Diatonic interval: MINOR_SECOND & MAJOR_SECOND
 * <ul>
 * <li>Numerator: number of diatonic intervals</li>
 * <li>Denominator: number of intervals</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - melody doesn't have diatonic intervals</li>
 * <li>1.0 - every interval is diatonic</li>
 * </ul>
 */
public class DiatonicStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.diatonic()) {
            numerator++;
        }
        denominator++;
    }
}
