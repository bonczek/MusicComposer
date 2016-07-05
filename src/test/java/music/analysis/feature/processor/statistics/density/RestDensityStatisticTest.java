package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.statistics.StatisticCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestDensityStatisticTest extends StatisticCounterTest<RestDensityStatistic> {

    @Override
    protected RestDensityStatistic initStatistic() {
        return new RestDensityStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.1875;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getDenominator(), is(0.0));
        assertThat(statistic.getNumerator(), is(0.0));
    }
}