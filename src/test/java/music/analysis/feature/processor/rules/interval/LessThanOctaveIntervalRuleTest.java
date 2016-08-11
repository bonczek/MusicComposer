package music.analysis.feature.processor.rules.interval;

import music.analysis.feature.processor.rules.IntervalRule;

public class LessThanOctaveIntervalRuleTest extends IntervalCounterTest<IntervalRule> {

    @Override
    protected IntervalRule initFeatureCounter() {
        return new IntervalRule(i -> !i.moreThanOctave());
    }

    @Override
    protected double getExpectedResult() {
        return 3.0;
    }
}