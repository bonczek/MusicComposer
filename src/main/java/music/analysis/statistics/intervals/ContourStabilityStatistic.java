package music.analysis.statistics.intervals;

import music.notes.pitch.Interval;

public class ContourStabilityStatistic extends IntervalStatistic {

    private Interval previousInterval = null;

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
