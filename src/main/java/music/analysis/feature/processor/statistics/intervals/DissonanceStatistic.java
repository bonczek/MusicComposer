package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.PitchInterval;

/**
 * Statistic that count dissonant intervals in melody.
 * Numerator: number of dissonance intervals
 * Denominator: number of intervals
 * <p>
 * Result near 0.0 value means that there is no dissonances.
 * On the other side for result near 1.0 value all intervals are dissonances.
 * <p>
 * Actually dissonance intervals include TRITONE and MAJOR_SEVENTH.
 */
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
