package music.analysis.feature.processor.rules;

public class RestRuleTest extends RuleCounterTest<RestRule> {

    @Override
    protected RestRule initFeatureCounter() {
        return new RestRule();
    }

    @Override
    protected double getExpectedResult() {
        return 12.0;
    }
}
