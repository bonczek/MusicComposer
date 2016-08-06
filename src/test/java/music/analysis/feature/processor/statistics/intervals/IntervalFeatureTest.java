package music.analysis.feature.processor.statistics.intervals;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class IntervalFeatureTest<T extends IntervalStatistic> extends FeatureCounterTest<T> {

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0));
        assertThat(featureCounter.getDenominator(), is(0));
        assertThat(featureCounter.getPreviousPitch(), nullValue());
    }
}
