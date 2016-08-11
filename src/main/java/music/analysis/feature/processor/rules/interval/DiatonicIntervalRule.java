package music.analysis.feature.processor.rules.interval;

import music.notes.pitch.Interval;

public class DiatonicIntervalRule extends IntervalRule {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.diatonic()) {
            ruleCounter += 1.0;
        }
    }
}
