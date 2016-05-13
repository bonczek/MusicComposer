package music.analysis.towsey.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.PitchInterval;

public class DiatonicStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.getPitchInterval().equals(PitchInterval.MINOR_SECOND) || interval.getPitchInterval().equals
                (PitchInterval.MAJOR_SECOND)) {
            numerator++;
        }
        denominator++;
    }
}
