package music.analysis.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.Octave;

public class OctaveDifferenceStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        numerator += Math.abs(interval.getOctaveDifference());
        denominator += Octave.values().length;
    }
}
