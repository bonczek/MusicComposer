package music.analysis.feature.processor.statistics.intervals;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContourStabilityStatisticTest extends IntervalStatisticTest<ContourStabilityStatistic> {

    @Override
    protected ContourStabilityStatistic initStatistic() {
        return new ContourStabilityStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.2857142857142857;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        assertThat(statistic.getPreviousInterval(), is(nullValue()));
    }
}