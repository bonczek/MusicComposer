package music.analysis.feature.processor.rules.interval;

import music.notes.pitch.Interval;

public class ConsonancesRule extends IntervalRule {

    @Override
    protected void processInterval(Interval interval) {
        if (interval.perfectConsonance()) {
            ruleCounter += 1.0;
        } else if (interval.imperfectConsonance()) {
            ruleCounter += 0.5;
        }
    }
}
