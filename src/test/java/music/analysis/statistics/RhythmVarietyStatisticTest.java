package music.analysis.statistics;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RhythmVarietyStatisticTest extends StatisticCounterTest<RhythmVarietyStatistic> {

    private static final int RHYTHM_DENOMINATOR = 16;

    @Override
    protected RhythmVarietyStatistic initStatistic() {
        return new RhythmVarietyStatistic(RHYTHM_DENOMINATOR);
    }

    @Override
    protected double getExpectedResult() {
        return 0.3125;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getDenominator(), is(RHYTHM_DENOMINATOR));
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getRhythmSetSize(), is(0));
    }
}