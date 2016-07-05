package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

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
