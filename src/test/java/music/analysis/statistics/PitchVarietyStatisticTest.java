package music.analysis.statistics;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchVarietyStatisticTest extends StatisticCounterTest<PitchVarietyStatistic> {

    @Override
    protected PitchVarietyStatistic initStatistic() {
        return new PitchVarietyStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.7777777777777778;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getPitchSetSize(), is(0));
        assertThat(statistic.getDenominator(), is(0));
        assertThat(statistic.getNumerator(), is(0));
    }


}