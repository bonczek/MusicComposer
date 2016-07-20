package music.analysis.feature.processor.rules;

import music.notes.pitch.Interval;

public class ConsonancesRule extends IntervalRule {

    @Override
    protected void processInterval(Interval interval) {
        if (!interval.moreThanOctave() && interval.perfectConsonance()) {
            ruleCounter += 1.0;
        } else if (!interval.moreThanOctave() && interval.imperfectConsonance()) {
            ruleCounter += 0.5;
        }
    }
}
