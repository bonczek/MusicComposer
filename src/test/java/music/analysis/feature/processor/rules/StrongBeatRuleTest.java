package music.analysis.feature.processor.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StrongBeatRuleTest extends RuleCounterTest<StrongBeatRule> {

    @Override
    protected StrongBeatRule initFeatureCounter() {
        return new StrongBeatRule();
    }

    @Override
    protected double getExpectedResult() {
        return 4.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        assertThat(featureCounter.getMelodyTime(), is(0.0));
    }
}