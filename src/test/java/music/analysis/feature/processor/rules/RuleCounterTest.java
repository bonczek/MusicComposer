package music.analysis.feature.processor.rules;

import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class RuleCounterTest<T extends DoubleFeatureCounter> extends FeatureCounterTest<T> {

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getResult(), is(0.0));
    }
}
