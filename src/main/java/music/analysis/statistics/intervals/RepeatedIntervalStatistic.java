package music.analysis.statistics.intervals;

import music.notes.pitch.Interval;

import java.util.HashSet;
import java.util.Set;

public class RepeatedIntervalStatistic extends IntervalStatistic {

    private Set<Interval> intervalSet = new HashSet<>();

    @Override
    protected void processInterval(Interval interval) {
        if (intervalSet.contains(interval)) {
            numerator++;
        } else {
            intervalSet.add(interval);
        }
        denominator++;
    }

    @Override
    public void clear() {
        super.clear();
        intervalSet.clear();
    }
}
