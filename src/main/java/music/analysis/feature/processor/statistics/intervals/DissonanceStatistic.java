package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.PitchInterval;

public class DissonanceStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if ((interval.getPitchInterval().equals(PitchInterval.TRITONE) ||
                interval.getPitchInterval().equals(PitchInterval.MAJOR_SEVENTH))) {
            numerator++;
        }
        denominator++;
    }
}
