package music.analysis.feature.processor.statistics.intervals;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContourStabilityFeatureTest extends IntervalFeatureTest<ContourStabilityStatistic> {

    @Override
    protected ContourStabilityStatistic initFeatureCounter() {
        return new ContourStabilityStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.2857142857142857;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        assertThat(featureCounter.getPreviousInterval(), is(nullValue()));
    }
}