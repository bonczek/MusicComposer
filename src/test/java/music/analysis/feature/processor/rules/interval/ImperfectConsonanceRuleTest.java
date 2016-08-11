package music.analysis.feature.processor.rules.interval;

import music.analysis.feature.processor.rules.IntervalRule;
import music.notes.pitch.Interval;

public class ImperfectConsonanceRuleTest extends IntervalCounterTest<IntervalRule> {

    @Override
    protected IntervalRule initFeatureCounter() {
        return new IntervalRule(Interval::imperfectConsonance);
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }
}
