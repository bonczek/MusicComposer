package music.analysis.feature.processor.rules.interval;

public class LessThanOctaveIntervalRuleTest extends IntervalCounterTest<LessThanOctaveIntervalRule> {

    @Override
    protected LessThanOctaveIntervalRule initFeatureCounter() {
        return new LessThanOctaveIntervalRule();
    }

    @Override
    protected double getExpectedResult() {
        return 3.0;
    }
}