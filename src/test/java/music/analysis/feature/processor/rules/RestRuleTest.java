package music.analysis.feature.processor.rules;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestRuleTest extends FeatureCounterTest<RestRule> {

    @Override
    protected RestRule initFeatureCounter() {
        return new RestRule();
    }

    @Override
    protected double getExpectedResult() {
        return 12.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.ruleCounter, is(0.0));
    }
}
