package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.IntervalDirection;

/**
 * Analyze directions of intervals in melody.
 * <ul>
 * <li>Numerator: number of up intervals</li>
 * <li>Denominator: number of intervals</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - all intervals are directed down or on the same degree</li>
 * <li>1.0 - all intervals are directed up</li>
 * </ul>
 */
public class ContourDirectionStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.getDirection().equals(IntervalDirection.UP)) {
            numerator++;
        }
        denominator++;
    }
}
