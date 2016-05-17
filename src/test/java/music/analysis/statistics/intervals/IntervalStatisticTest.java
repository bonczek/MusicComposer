package music.analysis.statistics.intervals;

import music.analysis.statistics.StatisticCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class IntervalStatisticTest<T extends IntervalStatistic> extends StatisticCounterTest<T> {

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getDenominator(), is(0));
        assertThat(statistic.getPreviousPitch(), nullValue());
    }
}
