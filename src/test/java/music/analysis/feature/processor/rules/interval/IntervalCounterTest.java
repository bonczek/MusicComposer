package music.analysis.feature.processor.rules.interval;

import music.analysis.feature.processor.rules.RuleCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class IntervalCounterTest<T extends IntervalRule> extends RuleCounterTest<T> {

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        assertThat(featureCounter.getPreviousPitch(), is(nullValue()));
    }
}
