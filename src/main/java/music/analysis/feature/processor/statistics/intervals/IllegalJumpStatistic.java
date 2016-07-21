package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

public class IllegalJumpStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.moreThanOctave()) {
            numerator++;
        }
        denominator++;
    }
}
