package music.analysis.feature.processor.rules.interval;

import music.notes.pitch.Interval;

public class LessThanOctaveIntervalRule extends IntervalRule {

    @Override
    protected void processInterval(Interval interval) {
        if (!interval.moreThanOctave()) {
            ruleCounter += 1.0;
        }
    }
}
