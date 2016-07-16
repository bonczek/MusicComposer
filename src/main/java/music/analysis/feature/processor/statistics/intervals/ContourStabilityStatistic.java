package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

/**
 * Analyze stability of interval changes in melody.
 * <ul>
 * <li>Numerator: number of adjacent intervals in same direction</li>
 * <li>Denominator: number of intervals - 1</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - direction is changed all the time</li>
 * <li>1.0 - intervals are directed in same way</li>
 * </ul>
 */
public class ContourStabilityStatistic extends IntervalStatistic {

    private Interval previousInterval = null;

    public Interval getPreviousInterval() {
        return previousInterval;
    }

    @Override
    protected void processInterval(Interval interval) {
        if (previousInterval == null) {
            previousInterval = interval;
            return;
        }
        if (previousInterval.getDirection().equals(interval.getDirection())) {
            numerator++;
        }
        previousInterval = interval;
        denominator++;
    }

    @Override
    public void clear() {
        super.clear();
        previousInterval = null;
    }
}
