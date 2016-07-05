package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.IntervalDirection;

public class ContourDirectionStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.getDirection().equals(IntervalDirection.UP)) {
            numerator++;
        }
        denominator++;
    }
}
