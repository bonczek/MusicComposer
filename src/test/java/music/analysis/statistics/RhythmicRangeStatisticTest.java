package music.analysis.statistics;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RhythmicRangeStatisticTest extends StatisticCounterTest<RhythmicRangeStatistic> {

    private static final int RHYTHM_DENOMINATOR = 16;

    @Override
    protected RhythmicRangeStatistic initStatistic() {
        return new RhythmicRangeStatistic(RHYTHM_DENOMINATOR);
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getDenominator(), is(RHYTHM_DENOMINATOR));
        assertThat(statistic.getMaxRhythmValue(), is(Double.MIN_VALUE));
        assertThat(statistic.getMinRhythmValue(), is(Double.MAX_VALUE));
    }
}