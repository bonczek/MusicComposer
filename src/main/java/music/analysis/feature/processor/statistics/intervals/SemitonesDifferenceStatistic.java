package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

//@todo not used statistic
public class SemitonesDifferenceStatistic extends IntervalStatistic {

    @Override
    protected void processInterval(Interval interval) {
        numerator += Math.abs(interval.getSemitonesDifference());
        denominator += Pitch.MAX_MIDI_VALUE;
    }
}
