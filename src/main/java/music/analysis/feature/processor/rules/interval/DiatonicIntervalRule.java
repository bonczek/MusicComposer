package music.analysis.feature.processor.rules.interval;

import music.notes.pitch.Interval;
import music.notes.pitch.PitchInterval;

public class DiatonicIntervalRule extends IntervalRule {

    @Override
    protected void processInterval(Interval interval) {
        int semitonesDifference = Math.abs(interval.getSemitonesDifference());
        if ((Integer.compare(semitonesDifference, PitchInterval.MINOR_SECOND.semitones()) == 0) ||
                (Integer.compare(semitonesDifference, PitchInterval.MAJOR_SECOND.semitones()) == 0)) {
            ruleCounter += 1.0;
        }
    }
}
