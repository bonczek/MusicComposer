package music.analysis.feature.processor.rules.interval;

import music.analysis.feature.processor.rules.IntervalRule;
import music.notes.pitch.Interval;

public class PerfectConsonanceRuleTest extends IntervalCounterTest<IntervalRule> {

    @Override
    protected IntervalRule initFeatureCounter() {
        return new IntervalRule(Interval::perfectConsonance);
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }
}