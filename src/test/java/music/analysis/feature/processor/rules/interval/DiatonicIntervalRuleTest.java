package music.analysis.feature.processor.rules.interval;

public class DiatonicIntervalRuleTest extends IntervalCounterTest<DiatonicIntervalRule> {

    @Override
    protected DiatonicIntervalRule initFeatureCounter() {
        return new DiatonicIntervalRule();
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }
}